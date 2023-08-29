package com.lec.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Entity(name = "t7_post")
@DynamicInsert  // insert 시 null 인 필드 제외
@DynamicUpdate  // update 시 null 인 필드 제외
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // 글 id (pk)

    @Column(nullable = false)
    private String subject;

    @Column(
            // 웹에디터를 사용하기 위한 대용량 컬럼 지정
            // ↓ ddl-auto: update  에선 동작하지 않으므로 create-drop 으로 적용해야 한다.
            columnDefinition = "LONGTEXT"      // ← MySQL, Postgre 의 경우
            // , length = 10000 // ← Oracle 의 경우 (varchar2(10000) 으로 지정됨)
    )
    private String content;

    @ColumnDefault(value = "0")
    private long viewCnt;

    // Post : User = N : 1      // 게시글을 조회할때 그 게시글의 작성자 조회하는 동작.
    @ManyToOne
    @ToString.Exclude
    private User user;      // 글 작성자 (fk)

    // 첨부파일
    // Post : File = 1 : N
    // CascadeType.ALL  : 부모의 삭제등의 동작 발생시 child 도 함께 삭제
    @OneToMany(/*mappedBy = "post",*/ cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Attachment> fileList = new ArrayList<>();

    // 댓글
    // Post : Comment = 1 : N
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    @Builder.Default
    private List<Comment> commentlist = new ArrayList<>();

    public void addFiles(Attachment... files){
        Collections.addAll(fileList, files);
    }

    public void addComments(Comment... comments){
        Collections.addAll(commentlist, comments);
    }
}
