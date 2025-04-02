package com.multi.semiproject.common;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class ResponseDTO {

    private int status;
    private String message;
    private Object data;

    public ResponseDTO(int status, String message, Object data){
        this.status =status;
        this.message = message;
        this.data = data;
    }

}
