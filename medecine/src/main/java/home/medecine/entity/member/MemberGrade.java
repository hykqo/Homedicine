package home.medecine.entity.member;

public enum MemberGrade {
    //관리자, 회원
    ROLE_ADMIN("관리자"), ROLE_MEMBER("일반사용자");

    private String description;

    MemberGrade(String description) {
        this.description = description;
    }
}
