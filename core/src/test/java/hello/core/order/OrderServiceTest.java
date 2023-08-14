package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void order(){

        Member member1 = new Member(1L, "memberA", Grade.BASIC);
        Member member2 = new Member(2L, "memberB", Grade.VIP);

        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(1L, "고래밥", 10000);
        Order order2 = orderService.createOrder(2L, "고래밥", 10000);

        Assertions.assertThat(order1.getDiscountPrice()).isEqualTo(0);
        Assertions.assertThat(order2.getDiscountPrice()).isEqualTo(1000);
        System.out.println("이슈 없이 완료");

    }



}
