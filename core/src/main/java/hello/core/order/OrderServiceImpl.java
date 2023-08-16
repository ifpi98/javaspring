package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

//주문이 들어오면
public class OrderServiceImpl implements OrderService{

    //오더서비스는 멤버 리포지터리에 가서 회원을 찾아야 한다.
    // 오더 서비스는 할인을 적용해야 한다.
    // 주문 도메인 전체와 클래스 다이어 그램을 참조하여 확인한다.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //멤버 찾는다
        Member member = memberRepository.findById(memberId);
        //할인을 받는지는 모르겠고,discountPolicy너가 알아서 해라, 할인하는 곳에 멤버를 넘긴다.
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //이제 order만들어서 반환해 준다.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
