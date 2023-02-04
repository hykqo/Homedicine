package home.medecine.service;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.member.Member;

public interface MemberService {
    Member Join(MemberDTO.Join join);
    Member delete(MemberDTO.MemberInfo member);
    void checkId(String id);
    void checkPhone(String phone);
    void checkEmail(String email);
    public MemberDTO.MemberInfo findById(final Long id);
    public MemberDTO.FindIdDTO findIdByName$Phone(MemberDTO.FindIdDTO findIdInfo);
    public Member findById$Name$Phone(MemberDTO.FindPwDTO findPwDTO);
    public MemberDTO.MemberInfo formatMemberInfo(final Member member);
    public void updatePassword(Member member, String pw);
}
