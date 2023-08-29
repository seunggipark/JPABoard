package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity(name = "t7_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String username;    // 회원 아이디

    @Column(nullable = false)
    private String password;    // 회원 비밀번호

    @ToString.Exclude   // Lombok 의 ToString에서 제외할 필드 설정 어노테이션
    @JsonIgnore // JSON 변환시 이 필드는 제외
    @Transient  // 데이터베이스 컬럼 저장되지 않게 제외시키기!!!!!!!
    private String re_password; // 비밀번호 확인 입력

    @Column(nullable = false)
    private String name;    // 회원이름

    // User : Authority = N : M
    @ToString.Exclude
    @Builder.Default
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public void addAuthority(Authority... authorities){
        if(authorities != null){
            Collections.addAll(this.authorities, authorities);
        }
    }
}
