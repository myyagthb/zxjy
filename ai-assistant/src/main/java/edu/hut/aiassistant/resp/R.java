package edu.hut.aiassistant.resp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class R {
    private Integer code;
    private String msg;
    private Object content;
}
