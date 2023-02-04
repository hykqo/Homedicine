package home.medecine.serviceImpl.member;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.member.Member;
import home.medecine.entity.member.MemberGrade;
import home.medecine.repository.MemberJpaRepository;
import home.medecine.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService {

    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member Join(MemberDTO.Join join) {
        checkId(join.getId());
        checkId(join.getPhone());
        Member member = Member.SIGN_UP(join, passwordEncoder.encode(join.getPw()), MemberGrade.ROLE_MEMBER);
        memberJpaRepository.save(member);
        return member;
    }

    @Override
    public Member delete(MemberDTO.MemberInfo member) {
        return null;
    }

    @Override
    public void checkId(String id) {
        Optional<Member> member = memberJpaRepository.findByMbId(id);
        member.ifPresent(m -> new MemberException.IdDuplicateException(id));
    }

    @Override
    public void checkPhone(String phone) {
        Optional<Member> member = memberJpaRepository.findByPhone(phone);
        member.ifPresent(m-> new MemberException.PhoneDuplicateException(phone));
//        if(mPhone != null) throw new Exception("이미 사용중힌 번호입니다. 동일한 증상이 여러번 발생할 경우 관리자에게 문의 바랍니다.");
    }

    @Override
    public void checkEmail(String emaail) {
        Optional<Member> member = memberJpaRepository.findByPhone(emaail);
        member.orElseThrow(()->new MemberException.EmailDuplicateException(emaail));
    }

    @Override
    public MemberDTO.MemberInfo findById(final Long id){
        final Optional<Member> member = memberJpaRepository.findById(id);
        member.orElseThrow(()->new MemberException.MemberNotFoundException(id));
        return formatMemberInfo(member.get());
    }

    @Override
    public MemberDTO.MemberInfo formatMemberInfo(final Member member){
       MemberDTO.MemberInfo memberInfo = new MemberDTO.MemberInfo();
       return memberInfo.createMemberInfo(member);
    }

    @Override
    public MemberDTO.FindIdDTO findIdByName$Phone(MemberDTO.FindIdDTO findIdInfo) {
        Optional<Member> member = memberJpaRepository.findByNameAndPhone(findIdInfo.getName(), findIdInfo.getPhone());
        member.orElseThrow(()->new MemberException.Member$InfoNotFountException("[name:"+findIdInfo.getName()+", phone:"+findIdInfo.getPhone()+"], [result : 0]"));
        findIdInfo.setResponseID(member.get().getMbId());
        return findIdInfo;
    }

    @Override
    public Member findById$Name$Phone(MemberDTO.FindPwDTO findPwDTO) {
        Optional<Member> member = memberJpaRepository.findByMbIdAndNameAndPhone(findPwDTO.getId(), findPwDTO.getName(), findPwDTO.getPhone());
        member.orElseThrow(()->new MemberException.Member$InfoNotFountException("[id:"+findPwDTO.getId()+", name:"+findPwDTO.getName()+", phone:"+findPwDTO.getPhone()+"], [result : 0]"));
        return member.get();
    }

    @Override
    public void updatePassword(Member member, String pw) {
        member.updatePw(passwordEncoder.encode(pw));
    }
}
