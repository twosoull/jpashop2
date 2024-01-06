package jpabook2.jpashop2.service;

import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) //jUnit 사용할 때 Spring 같이 사용 표시
@SpringBootTest // 스프링 컨테이너 안에서 돌린다.
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    @Rollback(false) //테스트는 롤백시켜버림, 데이터를 보려면 롤백 false;
    public void 회원가입() throws Exception {
        //given //줄테니까
        Member member = new Member();
        member.setName("kim");
        
        //when //이렇게 했을 때
        Long saveId = memberService.join(member);

        //then //이게 되는지
        em.flush(); //인서트문 로그에서 확인하기
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");
        Member member2 = new Member();
        member2.setName("kim1");
        //when
        memberService.join(member1);
        memberService.join(member2);//중복 회원, 예외가 발생해야 한다!


        //then
       // fail("예외가 발생해야 한다.");

    }

}