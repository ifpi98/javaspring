package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Member member1 = new Member(1L, "memberA", Grade.BASIC);
        Member member2 = new Member(2L, "memberB", Grade.VIP);

        memberService.join(member1);
        memberService.join(member2);

        Order order1 = orderService.createOrder(1L, "고래밥", 20000);
        Order order2 = orderService.createOrder(2L, "고래밥", 20000);

        System.out.println(order1.toString());
        System.out.println(order2.toString());

        System.out.println(order1.calculatePrice() + "원을 지불하셨습니다.");
        System.out.println(order2.calculatePrice() + "원을 지불하셨습니다.");

    }

}
