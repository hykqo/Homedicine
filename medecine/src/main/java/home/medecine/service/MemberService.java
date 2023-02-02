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
}
