package hello.core.discount;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import hello.core.order.RateDiscountPoilcy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();
    RateDiscountPoilcy discountPoilcy = new RateDiscountPoilcy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void order(){

        Member member1 = new Member(1L, "memberA", Grade.BASIC);
        Member member2 = new Member(2L, "memberB", Grade.VIP);

        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(1L, "고래밥", 20000);
        Order order2 = orderService.createOrder(2L, "고래밥", 20000);

        int discount1 = discountPoilcy.discount(member1,20000);
        int discount2 = discountPoilcy.discount(member2,20000);

        Assertions.assertThat(discount1).isEqualTo(0);
        Assertions.assertThat(discount2).isEqualTo(2000);
        System.out.println("이슈 없이 완료");

    }
}
