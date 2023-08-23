package jpa.shop.service;

import jpa.shop.domain.item.Item;
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

    public Item findOnd(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    public List<Item> findItems() {
        return itemRepository.findALl();
    }
}
