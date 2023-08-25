package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @PostConstruct
    public void init(){
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findByID(itemId);
        model.addAttribute("item",item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity, Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        // 저장된 item을 모델에 담아서 뷰에 전달한다.

        return "basic/addForm";
    }


    public String addItemV2(@ModelAttribute("item") Item item,  Model model) {

        itemRepository.save(item);
//        model.addAttribute("item", item);          // 자동 추가

        return "basic/addForm";
    }

//    @ModelAttribute - 요청 파라미터 처리
//    @ModelAttribute 는 Item 객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법으로 입력해준다.
//      @ModelAttribute 의 중요한 기능 - Model에 지정한 객체를 자동으로 넣어준다.
//        모델에 데이터를 담을 때는 이름이 필요하다.이름은 @ModelAttribute에 지정한 name(value) 속성을 사용한다.
//    만약 다음과 같이 이름을 다르게 지정하면 다른 이름으로 모델에 포함된다.
//    @ModelAttribute("hello") Item item 이름을 hello로 지정


    public String addItemV3(@ModelAttribute Item item){
        itemRepository.save(item);
        return "basic/addForm";
    }

//    @ModelAttribute 의 이름을 생략하면 모델에 저장될 때 클래스명을 사용한다.Item 이때 클래스의 첫글자만 소문자로 변경해서 등록한다.

    public String addItemV4(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

//    @ModelAttribute 자체 생략 가능 
//      다 생략되어 어떤 형태로 입력된 것인지 알기 어려움

    // 새로 고침 문제를 해결하려면 상품 저장 후에 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로 리다이렉트를 호출해주면 된다.

    // PRG - Post Redirect Get
    // RedirectAttribute를 쓰는 것으로 수정할 수 있다.

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }


    // 그래서 저장이 잘 되었으면 상품 상세 화면에 "저장되었습니다." 라는 메시지를 보여달라는 요구사항이 왔다.


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findByID(itemId);
        model.addAttribute("item", item);

        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


}
