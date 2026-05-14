package com.my.member_app.repository;

import com.my.member_app.entity.Member;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// 유일하게 얘는 적어도 되고,안적어도 된다.
@Repository
//테이블과 연결할 Entity Class를 지정, 해당킄래스의 ID의 타입을 적는다 .
public interface MemberRepository extends JpaRepository<Member, Long> {
    //1 쿼리 메서드

    //2.JPQL

    //3, native query
    //이름 검색
    @Query(value = "SELECT * FROM members WHERE namve LIKE %:keyword% ORDER BY name ASC",nativeQuery = true)
    List<Member> searchByMane(@Param("keyword") String keyword);
    // 주소 검샋
    @Query(value = "SELECT * FROM members WHERE address LIKE %:keyword% ORDER BY address ASC",nativeQuery = true)
    List<Member> searchByAddress (@Param("keyword") String keyword);
}
