package jpabook2.jpashop2.repository;

import jpabook2.jpashop2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> { //Long은 아이디

    //findBy하고 Name이 있으면 select m from Member m where m.name =? 이라는 쿼리를 만들어버린다. 자동화 되어 있다..
    List<Member> findByName(String name);

}
