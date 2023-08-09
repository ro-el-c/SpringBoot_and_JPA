package jpa.shop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("M") // 상속 관계 매핑
@Getter @Setter
public class Movie extends Item {
    private String director;
    private String actor;
}
