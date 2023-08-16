package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
// 테스트에 익숙하지 않으므로
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        //멤버 아이디를 하나 만들고, 1
        Long memberId = 1l;

        // 새로운 멤버 하나를 만든다, memberA, vip회원임
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 회원가입함
        memberService.join(member);
        // 지금 회원가입한 이 회원이 주문을 한다. itemA, 10000원 짜리 상품을 구매함 => 주문이 생김
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        //주문을 출력해 본다.
        System.out.println("order = " + order);
        //지불한 가격을 출력해 본다.
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
}
