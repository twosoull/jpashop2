package jpabook2.jpashop2.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("A") //타입 지정
@Getter
@Setter
public class Album extends Item {

    private String artist;
    private String etc;
}
