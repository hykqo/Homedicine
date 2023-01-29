package home.medecine.serviceImpl;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.member.Member;
import home.medecine.repository.MemberJpaRepository;
import home.medecine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member Join(MemberDTO.Join join) throws Exception {
        checkId(join.getId());
        checkId(join.getPhone());
        Member member = Member.SIGN_UP(join, passwordEncoder.encode(join.getPw()));
        memberJpaRepository.save(member);
        return member;
    }


    @Override
    public Member delete(MemberDTO.MemberInfo member) {
        return null;
    }

    @Override
    public void checkId(String id) throws Exception {
        Member mId = memberJpaRepository.findByMbId(id).orElse(null);
        if(mId != null) throw new Exception("이미 사용중인 아이디입니다.");
    }

    @Override
    public void checkPhone(String phone) throws Exception {
        Member mPhone = memberJpaRepository.findByPhone(phone).orElse(null);
        if(mPhone != null) throw new Exception("이미 사용중힌 번호입니다. 동일한 증상이 여러번 발생할 경우 관리자에게 문의 바랍니다.");
    }
}
