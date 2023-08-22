package hello.itemservice;


import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // 상황
        // itemA, 10000, 10
        Item item = new Item("itemA", 10000, 10);

        // when
        Item saveItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findByID(item.getId());
        Assertions.assertThat(findItem).isEqualTo(saveItem);
        System.out.println("OK");
    }

    @Test
    void findAll() {

        Item item1 = new Item("itemA", 10000, 10);
        Item item2 = new Item("itemB", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> result = itemRepository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(item1,item2);


    }

    @Test
    void update() {
        Item item1 = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item1);
        Long itemID = savedItem.getId();

        Item item2 = new Item("itemB", 20000, 20);
        itemRepository.update(itemID, item2);

        Item findItem = itemRepository.findByID(1L);

        Assertions.assertThat(item2.getItemName()).isEqualTo(findItem.getItemName());
        Assertions.assertThat(item2.getPrice()).isEqualTo(findItem.getPrice());
        Assertions.assertThat(item2.getQuantity()).isEqualTo(findItem.getQuantity());

    }



}
