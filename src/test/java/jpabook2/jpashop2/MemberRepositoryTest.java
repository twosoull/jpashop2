package jpabook2.jpashop2;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test //테스트가 끝나면 다 롤백 된다. 오류만 안나면 된다.
    @Transactional
    @Rollback(false) //롤백 안나게 하기
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("홍길동");

        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); //같은 곳에 있는 영속성 틀래스의 경우 같은 것으로 인지 한다. 캐쉬 안에 같은 것으로 받아들이게 된다.
        
    }






}