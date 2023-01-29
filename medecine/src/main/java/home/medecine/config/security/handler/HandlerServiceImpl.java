package home.medecine.config.security.handler;

import home.medecine.entity.member.LoginFailed;
import home.medecine.entity.member.LoginFailedStatus;
import home.medecine.entity.member.Member;
import home.medecine.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Primary
public class HandlerServiceImpl implements HandlerService{

    private final MemberJpaRepository memberJpaRepository;

    private static final int MAX_ATTEMPT = 5;
    private static final int LOCK_TIME = (1000 * 60) * 100; //1일


    //계정잠금
    @Override
    public void BadCredendtial(String id) {
        Member member = memberJpaRepository.findByMbId(id).orElse(null);

        LoginFailed og = member.getLoginFailed();
        int failedAttempt = og.getFailedAttempt();
        LocalDateTime lockTime = og.getLockTime();
        LoginFailedStatus status = LoginFailedStatus.UN_LOCK;

        //잠금이 안되어 있으면 로그인 최대 횟수 체크 후 잠금처리.
        if(!og.is_locked()) {
            if(failedAttempt >= MAX_ATTEMPT) status = LoginFailedStatus.LOCK;
            else failedAttempt+=1;
        }
        member.updateLoginFailed(new LoginFailed(failedAttempt, status, lockTime));
    }
}

