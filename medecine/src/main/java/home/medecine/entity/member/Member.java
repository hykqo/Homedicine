package home.medecine.entity.member;

import home.medecine.entity.Bag;
import home.medecine.entity.Ocr;
import home.medecine.entity.etc.BaseEntity;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "MEMBER")
@NoArgsConstructor
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
    private String id;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberGrade grade;

    @Embedded
    private LoginFailed loginFailed;

    public Long getIdx() {
        return idx;
    }

    public String getId() {
        return id;
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

    public LocalDateTime getBirth() {
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
}
