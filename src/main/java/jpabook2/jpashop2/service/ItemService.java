package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.item.Book;
import jpabook2.jpashop2.domain.item.Item;
import jpabook2.jpashop2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){

        //db에서 꺼낸 객체를 set을 해주면 자동으로 jpa에서 관리하기 때문에 db에서 변경이 된다.@Transactional로 인해 커밋돤다.
        Item findItem = itemRepository.findOne(itemId);

        //findItem.change(price,name,stockQuantity); 실무에서는 아래처럼 set을 사용하지 않고 의미있는 메서드를 사용해서 변경해야한다.
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }


    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
