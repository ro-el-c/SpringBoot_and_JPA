package jpa.shop.domain;

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
    @Column(name = "member_id")
    private Long id;
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // 회원:주문 = 1:N, Order 의 member 필드와 연결
    // mappedBy : 거울
    private List<Order> orders = new ArrayList<>( );
}
