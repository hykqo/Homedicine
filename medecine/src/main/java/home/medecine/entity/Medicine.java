package home.medecine.entity;

import home.medecine.entity.etc.BaseEntity;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity(name = "MEDICINE")
@NoArgsConstructor
public class Medicine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(nullable = false)
    private String name; //이름

    @Column(nullable = false)
    private String grade; //구분

    @Column(nullable = false)
    private String shape; //모양

    @Column(nullable = false)
    private String color; //색상

    @Column(nullable = false)
    private String type; //제형

    @Column(nullable = false)
    private String line; //분할선

    @Column(nullable = false)
    private String effect; //효능효과

    @Column(nullable = false)
    private String capa; //용법용량

    @Column(nullable = false)
    private String caution; //주의사항

    @Column(nullable = false)
    private String save; //저장방법

    @Column(nullable = false)
    private String ingredient; //성분정보

    @Column(nullable = false)
    private String company; //업체명

    @Column(nullable = false)
    private String classification; //삭약처 분류

    @Column(nullable = false)
    private String appr;//성상

    public Long getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getGrade() {
        return grade;
    }

    public String getShape() {
        return shape;
    }

    public String getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public String getLine() {
        return line;
    }

    public String getEffect() {
        return effect;
    }

    public String getCapa() {
        return capa;
    }

    public String getCaution() {
        return caution;
    }

    public String getSave() {
        return save;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getCompany() {
        return company;
    }

    public String getClassification() {
        return classification;
    }

    public String getAppr() {
        return appr;
    }
}
