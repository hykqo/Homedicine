package home.medecine.config.security;

import home.medecine.entity.member.Member;
import home.medecine.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberDetailsService implements UserDetailsService {

    private final MemberJpaRepository memberJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberJpaRepository.findByMbId(username).orElse(null);
//        member.orElseThrow(() ->
//                new UsernameNotFoundException(username));

        return new MemberDetails(member);
    }
}
