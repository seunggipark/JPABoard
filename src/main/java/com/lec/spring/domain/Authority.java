package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t7_authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // PK

    @Column(length = 40, nullable = false, unique = true)   // 길이는 40글자 / null 허용하지않고/ 유니크하게
    private String name;    // 권한명 ex) "ROLE_MEMBER", "ROLE_ADMIN"
}
