package jpabook2.jpashop2.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded // jpa 내장타입 일 수 있다는 표시
    private Address address;

    @OneToMany(mappedBy = "member") //하나의 member에서 여러개의 order이다. mappedBy를 연관관계의 주인을 지정한다. 주인인 order 클래스 쪽은 그대로 두고 아래의 무엇으로 지정이 되는지 필드명을 적어준다. 이곳의 값을 변경한다고 해서 fk값이 변경되지 않는다.
    private List<Order> orders = new ArrayList<>();

}
