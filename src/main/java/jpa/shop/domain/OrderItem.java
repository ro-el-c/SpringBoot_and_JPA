package jpa.shop.domain;

import jakarta.persistence.*;
import jpa.shop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;


    /* @NoArgsConstructor(access = AccessLevel.PROTECTED) 로 대체 */
//    protected OrderItem() {
//        // createOrderItem 외의 생성자 사용 막음
//    }

    // 생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);

        return orderItem;
    }

    // 비즈니스 로직
    public void cancel() {
        getItem().addStock(count); // 재고수량 원상복구
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
