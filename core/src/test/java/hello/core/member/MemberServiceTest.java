package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test
    void join(){

        // 상황
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 처리
        memberService.join(member);

        // 결과
        Member findMember = memberService.findMember(1L);
        Assertions.assertThat(member).isEqualTo(findMember);
        System.out.println("완료");

    }


}
