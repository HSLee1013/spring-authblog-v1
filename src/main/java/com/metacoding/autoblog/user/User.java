package com.metacoding.autoblog.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_tb")
@Getter
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    private Timestamp createdAt;

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    // 권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    // 휴면 계정 확인
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 로그인 시도 횟수 초과 잠김 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 확인 비밀번호 변경해야함
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 상태 확인
    @Override
    public boolean isEnabled() {
        return true;
    }
}
