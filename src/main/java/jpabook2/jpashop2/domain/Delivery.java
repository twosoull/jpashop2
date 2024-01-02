package jpabook2.jpashop2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  //EnumType.ORDINAL 은 숫자로 들어간다. 만약 중간에 다른 상태가 생기면 망한다. 그러므로 꼭 스트링으로 사용해야한다.
    private DeliveryStatus status; //READY, COMP
}
