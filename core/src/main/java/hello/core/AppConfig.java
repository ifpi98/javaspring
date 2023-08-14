package hello.core;

import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.FixDiscountPolicy;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // appconfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceimpl을 생성하면서 생성자로 전달한다.
    // = new MemoryMemberRepository();
    // 의존성 주읩 (dependency injection)

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
