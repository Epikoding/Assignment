package com.sparta.assignment.controller;

import com.sparta.assignment.domains.Assignment;
import com.sparta.assignment.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//RestController는 data'만' 반환할 때 사용, Controller를 body에 html을 던져줄 때 사용.
@Controller
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentRepository repository;

    @GetMapping("/detail/page/{id}")
    // detail응답으로 넘겨줄 때 model을 사용. thymeleaf는 java의 jinja2와 같다.
    // Model은 Jinja2와 비슷한 역할을 한다. id를 PathVariable로 받고, Model란 장비를 통해 model을 템플릿 엔진에 넘겨준다.
    public String readPost(@PathVariable Long id, Model model) {
        System.out.println(id);
//      assignment는 domains에 있는 assignment.java
        Assignment assignment = repository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재 하지 않습니다.")
        );
//      오류가 발생할 때 sout가 없어도 콘솔에 오류가 뜬다.

//      "post", assignment에서 "post"는 html로 넘어가는 key, assignment는 value인데, 그 value는 db에서 찾아온 값.
        model.addAttribute("post",assignment);
        return "detail";
    }
}

