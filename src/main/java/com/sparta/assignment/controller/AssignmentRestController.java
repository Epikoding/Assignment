package com.sparta.assignment.controller;

import com.sparta.assignment.domains.Assignment;
import com.sparta.assignment.domains.AssignmentRequestDto;
import com.sparta.assignment.domains.AssignmentUpdateDto;
import com.sparta.assignment.repository.AssignmentRepository;
import com.sparta.assignment.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AssignmentRestController {

    private final AssignmentRepository repository;
    private final AssignmentService service;

    @GetMapping("/api/post")
    public List<Assignment> readAllPost() {
        return repository.findAllByOrderByCreateAtDesc();
    }

//  Post를 보낼 땐 body가 있기 때문에 requestbody로 post를 받을 수 있는 것이고,
//  Get은 url을 통해 head만 받기 때문에(body없음) Pathrequest나 RequestParam을 사용해야 한다.
    @PostMapping("/api/createPost")
    public Assignment createPost(@RequestBody AssignmentRequestDto dto) {
        try {
//          dto는 table(entity)가 아니기에, dto 객체를 assignment 생성자를 통해서 객체(assignment) 생성.
            Assignment assignment = new Assignment(dto);
            repository.save(assignment);
            return assignment;

        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping("/api/updatePost/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody AssignmentUpdateDto dto) {
        try {
            return service.update(id, dto);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/api/deletePost/{id}")
    public Boolean deletePost(@PathVariable Long id, @RequestParam("password") String password) {
        try {
            Optional<Assignment> assignment = repository.findByIdAndPassword(id, password);
            if(assignment.isPresent()){
                repository.deleteById(id);
                return true;
            }else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
