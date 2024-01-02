package jpabook2.jpashop2.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B") //타입 지정
@Getter @Setter
public class Book extends Item{

    private String author;
    private String isbn;
}
