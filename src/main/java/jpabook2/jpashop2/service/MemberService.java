package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //조회의 경우 읽기 전용으로 사용하면 리소스 사용 많이하지 않고 단순히 읽기 전용으로만 불러와 준다.여기서는 읽기가 더 많으니 이렇게 읽기전용으로 둔다음
//스프링 제공의 어노테이션을 사용하는 것을 권장함 (트랜잭션)
//@AllArgsConstructor 모든 필드 값에 대하여 빈 생성
@RequiredArgsConstructor // final이 붙은 필드값만 생성 *생성자 인젝션이란? 생성자를 통해 의존성 주입
public class MemberService {

    private final MemberRepository memberRepository; //요즘에는 final을 붙이는 추세임..바꾸지 않기 때문

   /* RequiredArgsConstructor 를 사용해서 생략 됌
   public MemberService(MemberRepository memberRepository) { //테스트 코드 작성시 사용 및 생성할때 런타임 로딩 시 자동으로 memberRepository 생성
        this.memberRepository = memberRepository;
    }*/
    //회원 가입

    @Transactional //가입, 즉 save일 때에만 이렇게 따로 트랜잭션 어노테이션을 사용해준다. 디폴트는 readOnly = false임
    public Long join(Member member){
        // 중복 가입 회원 검증
        validateDuplicateMember(member);
      memberRepository.save(member);
      return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // 이렇게 EXCEPTION 으로 가입을 막을 수 있지만, db에 name을 유니크로 만들어 최후의 방어까지 하면 좋다.
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 상세 조회
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
