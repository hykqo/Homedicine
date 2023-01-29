package home.medecine.service;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.member.Member;

public interface MemberService {
    Member Join(MemberDTO.Join join) throws Exception;
    Member delete(MemberDTO.MemberInfo member);
    void checkId(String id) throws Exception;
    void checkPhone(String phone) throws Exception;
}
