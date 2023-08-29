package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class QryCommentList extends QryResult{

    @JsonProperty("data")   // JSON 변환시 "data" 란 이름의 property 로 변환됨.
    List<Comment> list;
}
