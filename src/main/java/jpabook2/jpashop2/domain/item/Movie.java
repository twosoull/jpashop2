package jpabook2.jpashop2.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") //타입 지정
@Getter
@Setter
public class Movie extends Item {
    private String director;
    private String actor;

}
