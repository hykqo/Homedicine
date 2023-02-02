package home.medecine.dto;

import home.medecine.entity.Bag;
import home.medecine.entity.Ocr;
import home.medecine.entity.member.Member;
import home.medecine.entity.member.MemberGrade;
import home.medecine.entity.member.MemberStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDTO {

    @Getter
    @NoArgsConstructor
    public static class Join {
        @NotNull
        @NotEmpty //String 전용
        private String id;
        @NotNull
        @NotEmpty
        private String pw;
        @NotNull
        @NotEmpty
        private String email;
        @NotNull
        @NotEmpty
        private String name;
        @NotNull
        @NotEmpty
        private String phone;
        @NotNull
        private LocalDate birth;
        private MemberStatus status;
    }

    @Data
    public static class MemberInfo {
        private Long idx;
        private String id;
        private String name;
        private String email;
        private String phone;
        private LocalDate birth;
        private MemberGrade grade;
        private MemberStatus status;
        private List<Bag> bags;
        private List<Ocr> ocr;
        private LocalDateTime createDate;

        public MemberInfo createMemberInfo(Member member){
            MemberInfo memberInfo = new MemberInfo();
            memberInfo.idx = member.getIdx();
            memberInfo.id = member.getMbId();
            memberInfo.name = member.getName();
            memberInfo.email = member.getEmail();
            memberInfo.phone = member.getPhone();
            memberInfo.birth = member.getBirth();
            memberInfo.grade = member.getGrade();
            memberInfo.status = member.getStatus();
            memberInfo.createDate = member.getCreateDate();
            return memberInfo;
        }
    }


}
