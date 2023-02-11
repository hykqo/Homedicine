package home.medecine.config.security.service;

import home.medecine.config.error.ErrorResponse;
import home.medecine.config.error.exception.ErrorCode;
import home.medecine.config.security.exception.SecurityCustomException;
import home.medecine.entity.member.LoginFailed;
import home.medecine.entity.member.LoginFailedStatus;
import home.medecine.entity.member.Member;
import home.medecine.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@RequiredArgsConstructor
public class FailureServiceImpl implements FailureService{

    private final MemberJpaRepository memberJpaRepository;

    private static final int MAX_ATTEMPT = 5;
    private static final int LOCK_HOUR = 3; //3시간;


    @Override
    @Transactional
    public ErrorResponse handleBadCredentials(String id) {
        Member member = memberJpaRepository.findByMbId(id).orElse(null);
        if(member != null){
            LoginFailed og = member.getLoginFailed();
            //잠금처리 되어 있지 않으면
            if (og.is_locked()) {
                if(og.getFailedAttempt() >= MAX_ATTEMPT) return ErrorResponse.of(ErrorCode.ACCOUNT_LOCKED);
            }
            else {
                if (og.getFailedAttempt() < MAX_ATTEMPT) member.updateLoginFailed(LoginFailed.AddCount(og.getFailedAttempt()));
                else member.updateLoginFailed(LoginFailed.ChangeToLock(MAX_ATTEMPT, LOCK_HOUR));
            }
        }
        return ErrorResponse.of(ErrorCode.BAD_CREDENTIALS);
    }
}
