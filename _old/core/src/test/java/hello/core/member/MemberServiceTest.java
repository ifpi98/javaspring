package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    //AppConfig()를 생성하고 memberService에 넣어서 위의 memberService에 할당한 후 테스트 한다
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){
        //given 상황
        //테스트를 회원을 생성한다 (id는1, 이름은 name, 회원등급은 VIP)
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when 처리
        //멤버를 가입시킨다
        memberService.join(member);
        // 회원저장소에서 가입한 멤버를 찾아본다
        Member findMember = memberService.findMember(1L);

        //then 결과
        //내가 가입시킨 멤버와 찾은 멤버가 같으면 ok
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
