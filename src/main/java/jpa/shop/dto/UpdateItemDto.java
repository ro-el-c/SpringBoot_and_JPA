package jpa.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class UpdateItemDto {
    private String name;
    private int price;
    private int stockQuantity;
}
