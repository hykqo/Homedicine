package home.medecine.config.security;

import home.medecine.entity.member.LoginFailed;
import home.medecine.entity.member.LoginFailedStatus;
import home.medecine.entity.member.Member;
import home.medecine.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HandlerService implements HandlerImpl{

    private final MemberJpaRepository memberJpaRepository;

    private static final int MAX_ATTEMPT = 5;
    private static final int LOCK_TIME = (1000 * 60) * 100;


    //계정잠금
    @Override
    public void BadCredendtial(String id) {
        Member member = memberJpaRepository.findById(id);
        LoginFailed og = member.getLoginFailed();
        if(og.is_locked()) {
            if(og.getFailedAttempt() >= MAX_ATTEMPT){
                member.updateLoginFailed(new LoginFailed(og.getFailedAttempt(), LoginFailedStatus.LOCK));
            }
        }
    }
}
