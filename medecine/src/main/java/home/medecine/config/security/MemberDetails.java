package home.medecine.config.security;

import home.medecine.entity.member.Member;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class MemberDetails implements UserDetails {

    private Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    //계정이 갖고 있는 권한 목록을 리턴한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority(member.getGrade().toString()));
        return collect;
    }

    @Override
    public String getPassword() {return member.getPw();}

    @Override
    public String getUsername() {return member.getMbId();}

    //계정이 만료되지 않은는지를 리턴한다(true : 만료되지 않음)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있지 않은지를 리턴한다(true : 잠겨있지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //계정의 패스워드가 만료되지 않았는지를 리턴한다(true : 만료되지 않음)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용가능한 계정인지 리턴한타(true : 사용가능한 계정)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
