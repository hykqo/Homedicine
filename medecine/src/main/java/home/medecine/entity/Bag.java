package home.medecine.entity;

import home.medecine.entity.etc.BaseEntity;
import home.medecine.entity.member.Member;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "BAG")
@NoArgsConstructor
public class Bag extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MB_IDX")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "MC_IDX")
    private Medicine medicine;


    public Long getId() {
        return id;
    }


    public Member getMember() {
        return member;
    }


    public Medicine getMedicine() {
        return medicine;
    }


    public void updateMedicine(Medicine medicine){
        this.medicine = medicine;
    }

    public void changeMember(Member member){
        this.member = member;
        member.getBags().add(this);
    }

}
