package com.sparta.assignment.repository;

import com.sparta.assignment.domains.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// JpaRepositry에 Assignment와 Long이라는 '타입'을 사용하여 AssignmentRepository라는 상속자(자식) 인터페이스를 생성.
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

//  외국인 -> 이동재(영어)JpaRepository의 상속인 -> 김현옥(치킨)JpaRespository
//  김현옥은 영어를 못하기에, 아들이 필요하다. 아들은 치킨을 못튀기기에 어머니가 필요하다. 외국인은 한국어를 못하기에 이동재가 필요하다.
//  즉, 외국인이 치킨을 먹으려고 이동재, JpaRepository의 상속인이 필요한 것이다.Ω
//  'findByIdAndPassword','findAllByOrderByCreateAtDesc'는 JpaRepository의 "자식"의 메소드이다. JpaRepository의 '메소드'가 아니다.
    Optional<Assignment> findByIdAndPassword(Long id, String password);
    List<Assignment> findAllByOrderByCreateAtDesc();

}
