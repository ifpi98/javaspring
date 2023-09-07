package hello.core.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}

//주문 도메인 전체를 보면
// 1. 주문생성 - 회원 id, 상품명, 상품가격