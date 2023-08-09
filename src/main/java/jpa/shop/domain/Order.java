package jpa.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY) // 주문:회원 = N:1
    // @XToOne 는 즉시 로딩 -> 지연 로딩 설정 ; FetchType.LAZY
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    /*
    * cascade = CascadeType.ALL
    * ->
    * persist(orderItemA)
    * persist(orderItemB)
    * persist(orderItemC)
    * persist(order)
    * 의 코드를
    *
    * persist(order)
    * 로 줄일 수 있음
    * */

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 - [ORDER, CANCEL]

}
