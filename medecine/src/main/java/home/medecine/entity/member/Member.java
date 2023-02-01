package home.medecine.entity.member;

import home.medecine.dto.MemberDTO;
import home.medecine.entity.Bag;
import home.medecine.entity.Ocr;
import home.medecine.entity.etc.BaseEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Entity(name = "MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Bag> bags;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Ocr> ocr;

    @Column(nullable = false)
    private String mbId;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @ColumnDefault("'ACTIVE'")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberGrade grade;

    @Embedded
    private LoginFailed loginFailed;

    public Long getIdx() {
        return idx;
    }

    public String getMbId() {
        return mbId;
    }

    public String getPw() {
        return pw;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public MemberStatus getStatus() {
        return status;
    }

    public MemberGrade getGrade() {
        return grade;
    }

    public List<Bag> getBags() {
        return bags;
    }

    public List<Ocr> getOcr() {
        return ocr;
    }

    public LoginFailed getLoginFailed() {
        return loginFailed;
    }

    public void updateLoginFailed(LoginFailed lf){
        this.loginFailed = lf;
    }

    public static Member SIGN_UP(final MemberDTO.Join join, final String encodePw, MemberGrade grade) {
        Member member = new Member();
        member.mbId = join.getId();
        member.pw = encodePw;
        member.email = join.getEmail();
        member.name = join.getName();
        member.birth = join.getBirth();
        member.phone = join.getPhone();
        member.grade = grade;
        member.status = MemberStatus.ACTIVE;
        return member;
    }
}
