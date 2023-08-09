package jpa.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // 배송 상태 - [READY, COMP]
    /*
     * EnumType.ORDINAL -> 숫자로 들어감
     * 다른 상태가 생기면 ... Noooo..
     *
     * EnumType.STRING 으로 사용할 것
     * */
}
