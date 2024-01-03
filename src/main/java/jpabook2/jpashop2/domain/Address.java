package jpabook2.jpashop2.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //jpa 내장타입
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { // 엔티티나 임베디드 타입은 jpa 스팩상에 기본생성자가 꼭 필요하다.
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
