package jpa.shop.service;

import jpa.shop.domain.item.Book;
import jpa.shop.domain.item.Item;
import jpa.shop.dto.UpdateItemDto;
import jpa.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /*
    * 단순히 ItemRepository 에 위임하는 역할을 함
    * */

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateItemDto itemDto) {
        Item findItem = itemRepository.findOne(itemId); // id 를 기반으로 실제 DB 에 존재하는 영속 상태 엔티티 찾아옴

        findItem.setName(itemDto.getName());
        findItem.setPrice(itemDto.getPrice());
        findItem.setStockQuantity(itemDto.getStockQuantity());
        // @Setter 가 아닌 다른 setter 메서드를 구현하여 사용하는 것이 더 좋음

    } // transactional 에 의하여 transaction commit 됨
      // -> JPA 가 flush -> 영속성 컨텍스트에 있는 엔티티의 변경을 전부 감지 -> 바뀐 부분을 DB 에 update

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findALl();
    }
}
