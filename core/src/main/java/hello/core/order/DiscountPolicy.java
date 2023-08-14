package hello.core.order;

import hello.core.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);
}
