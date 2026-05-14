package com.my.member_app.service;

import com.my.member_app.controller.MemberController;
import com.my.member_app.dto.MemberDto;
import com.my.member_app.entity.Member;
import com.my.member_app.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor

public class MemberService {
    //의존성 주입: 필요한 컴포넌트(인스턴스)를 불러오는 작업
    // 1. 첫번쨰 주입방법

  /*  @Autowired
    MemberRepository memberRepository;
*/
    //2. 생성자 주입 방법
    //3. @RequiredArgsConstructor
    private final MemberRepository memberRepository;
    /*    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        }*/

    public List<MemberDto> findAll() {
      //repository 에서 필요한 정보를 가져온다.
      //단, Repo는 ENtity만 사용한다 .
      List<Member> members = memberRepository.findAll();
      //Entity List - > Dto List 로 변환 한 후 리턴 한다.
      //깡통 DtoList 만들기
     // List<MemberDto> dtoList = new ArrayList<>();
      List<MemberDto> dtoList = new ArrayList<>(); // 이 라인을 추가하세
      for (Member member : members){
        dtoList.add(MemberDto.toDto(member));

      }
      //return droList;
//2 스트림을 이용해서 처리하기
      return members
              .stream()
              .map(x-> MemberDto.toDto(x))
              .toList();
    }


    public void insert(MemberDto dto) {
      //dto 를 -> 멤버로 변환
      Member member = MemberDto.toEntity(dto);
      memberRepository.save(member);
    }

  public void delete(Long deleteid) {
      memberRepository.deleteById(deleteid);
  }

  public MemberDto findById(Long updateid) {
      Optional<Member> member= memberRepository.findById(updateid);
// Optional 로 받은 객체가 비어있으면
      if (member.isPresent()) {
        return MemberDto.toDto(member.get());
      } else return null;
    }
}

