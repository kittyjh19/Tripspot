package com.multi.semiproject.board.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data


public class BoardDTO {
    private int board_no;
    private String board_type;
    private String title;
    private String content;
    private int view_count;
}
