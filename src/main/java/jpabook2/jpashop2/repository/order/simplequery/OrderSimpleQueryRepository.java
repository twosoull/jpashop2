package jpabook2.jpashop2.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.repository.OrderSimpleQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    public List<jpabook2.jpashop2.repository.OrderSimpleQueryDTO> findOrderDtos(){
        return em.createQuery("select new jpabook2.jpashop2.repository.OrderSimpleQueryDTO(o.id, m.name, o.orderDate, o.status, d.address)" +
                " from Order o "
                +"join o.member m " +
                "join o.delivery d", OrderSimpleQueryDTO.class).getResultList();
    }
}
