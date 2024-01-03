package jpabook2.jpashop2.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook2.jpashop2.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor //autowired가 지원하기에 이것 또한 가능하다.final만 생성하기
public class MemberRepository {

    //@PersistenceContext //자동으로 엔티티매니저를 만들어준다.
    //@Autowired jpa에서 이렇게도 지원해준다
    //private EntityManager em;

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){//sql이 아닌 jtgl 이다.
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from m where m.name = :name", Member.class)
                .setParameter("name", name).getResultList();
    }
}
