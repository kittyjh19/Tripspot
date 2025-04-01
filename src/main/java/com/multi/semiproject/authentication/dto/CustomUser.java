package com.multi.semiproject.authentication.dto;

import com.multi.semiproject.member.model.dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Setter
@ToString(exclude = "pw")
public class CustomUser implements UserDetails {

    private String id;

    private String pw;

    private String memberEmail;

    private Collection<? extends GrantedAuthority> authorities;

    public CustomUser(MemberDTO member, Collection<? extends GrantedAuthority> authorities){
        this.authorities =  authorities;
        this.id  = member.getId();
        this.pw  = member.getPw();
        this.memberEmail  = member.getMemberEmail();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return pw;
    }

    @Override
    public String getUsername() {
        return id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
