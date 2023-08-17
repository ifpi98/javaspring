package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyExcludeComponent {
}

//@Target() 어노테이션으로 필드, 메소드, 클래스, 파라미터 등 선언할 수 있는 타입을 설정
// @Retention() 어노테이션으로 어느 시점까지 어노테이션의 메모리를 가져갈 지 설정
// RetentionPolicy의 값을 넘겨주는 것으로 어노테이션의 메모리 보유 범위가 결정됨
// @Documented 클래스에 특수한 의미를 부여하거나 기능을 주입하기 위한 메타데이터