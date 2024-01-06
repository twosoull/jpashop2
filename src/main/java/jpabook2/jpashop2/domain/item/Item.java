package jpabook2.jpashop2.domain.item;

import jakarta.persistence.*;
import jpabook2.jpashop2.domain.Category;
import jpabook2.jpashop2.exception.NotEnoughtStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한테이블에 다 넣는다.
@DiscriminatorColumn(name= "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column (name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //setter로 service에서 변경하는 것이 아니라 필드 값은 필드를 가지고 있는 클래스 내에서 핵심 비즈니스 로직으로써 값을 변경해야한다.
    /***
     *
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughtStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }
}
