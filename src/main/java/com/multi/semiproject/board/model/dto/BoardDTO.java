package com.multi.semiproject.board.model.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
public class BoardDTO {
    private int boardNo;
    //@Value("POST")
    private String boardType = "POST";
    private String title;
    private String content;
    private String memberId;
    private int viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

}