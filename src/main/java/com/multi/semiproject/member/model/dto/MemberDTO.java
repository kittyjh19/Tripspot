package com.multi.semiproject.member.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "pw")

public class MemberDTO {

    private Long no;                      // 회원번호
    private String id;                    // 회원아이디
    private String pw;                    // 회원비밀번호
    private String name;                  // 회원이름
    private String memberRole = "ROLE_USER";            // 회원권한 (기본값: 'role_user')
    private String memberEmail;           // 이메일
    private LocalDateTime createdDate;    // 생성일시
    private String createdPerson;         // 생성자
    private LocalDateTime modifiedDate;   // 수정일시
    private String modifiedPerson;        // 수정자
}
