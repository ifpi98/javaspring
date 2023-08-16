package hello.core.discount;
// 할인정책이 잘 계산되었는지 확인한다.
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given  멤버를 하나 만든다.  1, memberVIP
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when  10000원의 할인 금액을 넘긴다.
        int discount = discountPolicy.discount(member, 10000);
        //then  할인 금액이 1000원이랑 같은지 확인
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x() {
        //given  멤버2을 만든다, 2, memberBASIC
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}