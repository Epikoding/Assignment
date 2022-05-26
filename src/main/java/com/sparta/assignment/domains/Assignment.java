package com.sparta.assignment.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Assignment extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String contents;

    @Column
    private String password;

    public Assignment(AssignmentRequestDto dto) {
        this.title = dto.getTitle();
        this.writer = dto.getWriter();
        this.contents = dto.getContents();
        this.password = dto.getPassword();
    }

    public void update(AssignmentUpdateDto dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
