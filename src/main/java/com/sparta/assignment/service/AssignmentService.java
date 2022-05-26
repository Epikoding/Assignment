package com.sparta.assignment.service;

import com.sparta.assignment.domains.Assignment;
import com.sparta.assignment.domains.AssignmentUpdateDto;
import com.sparta.assignment.repository.AssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
// 노동자같은거. 가공하는 곳.
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository repository;

    @Transactional
//  처리된 정보를 db에 반영할 때, @Transactional이 있어야한다.
//  long에 string 타입이 들어가면 오류가 발생할 것. Long은 reference type이라 return에 null이 들어가도 오류가 발생하지 않음.
//  null이 들어갔을 때 nullPointerException을 이용해 처리할 수 있으니까 long보다 Long을 사용하는 것이 권장됨.
    public Long update(Long id, AssignmentUpdateDto dto) {
        try {
            Assignment assignment = repository.findByIdAndPassword(id,dto.getPassword()).orElseThrow(
                    () -> new IllegalStateException("수정 권한이 없습니다.")
            );
            assignment.update(dto);
        } catch (IllegalStateException e) {
            return null;
        }

        return id;
    }

}