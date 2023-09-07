package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
    @ComponentScan(
//        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        // AutoAppConfig의 패키지는 hello.core이므로 해당 부분에 있는 것을 모두 찾는다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
    )

public class AutoAppConfig {

//    @Bean(name="memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }

}
