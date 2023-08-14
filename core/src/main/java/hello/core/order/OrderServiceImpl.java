package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy;
    // = new RateDiscountPoilcy();

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        Order order = new Order(memberId, itemName, itemPrice, discountPrice);
        return order;
    }
}
