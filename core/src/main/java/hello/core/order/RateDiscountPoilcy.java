package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;

import static java.lang.Long.parseLong;

public class RateDiscountPoilcy implements DiscountPolicy{

    double discountRate = 0.1;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return (int)(price * discountRate);
        } else {
            return 0;
        }
    }
}
