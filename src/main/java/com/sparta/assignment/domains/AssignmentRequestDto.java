package com.sparta.assignment.domains;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
// Tostring은 object안에 있는 내용을 string으로 보여준다.
// 만약 private Integar id가 있을지라도, 그 id는 string으로 반환된다.
public class AssignmentRequestDto {
    private String title;
    private String contents;
    private String writer;
    private String password;

}
