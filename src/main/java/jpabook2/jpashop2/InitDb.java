package jpabook2.jpashop2;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jpabook2.jpashop2.domain.*;
import jpabook2.jpashop2.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct //기동 시 딱한번 빈에 등록되며 사용된다.
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void dbInit1(){
            Member member = getMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = createBook("JPA book1", 10000, 100);
            em.persist(book1);

            Book book2 = createBook("JPA book2", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 1);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2(){
            Member member = getMember("userB", "서울2", "13", "11112");
            em.persist(member);

            Book book1 = createBook("spring book1", 20000, 100);
            em.persist(book1);

            Book book2 = createBook("spring book2", 40000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 14);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Member getMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
        private Book createBook(String JPA_book1, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(JPA_book1);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity); // command + option + p 누르면 파라미터로 변경됌
            return book1;
        }
    }

}

