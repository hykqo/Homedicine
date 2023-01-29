package home.medecine.dto;

import home.medecine.entity.Bag;
import home.medecine.entity.Ocr;
import home.medecine.entity.member.MemberGrade;
import home.medecine.entity.member.MemberStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class MemberDTO {

    @Data
    @NoArgsConstructor
    public static class Join {
        private String id;
        private String pw;
        private String email;
        private String name;
        private String phone;
        private LocalDateTime birth;
        private MemberGrade grade;
        private MemberStatus status;
    }

    @Data
    public static class MemberInfo {
        private Long idx;
        private String id;
        private String name;
        private String email;
        private String phone;
        private LocalDateTime birth;
        private MemberGrade grade;
        private MemberStatus status;
        private List<Bag> bags;
        private List<Ocr> ocr;
        private LocalDateTime createDate;

    }
}
