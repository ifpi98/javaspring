package Hello.hellospring.controller;

import Hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // 생성자에 @Autowired가 있으면 스프링이 연관된 객체를 스프링컨테이너에서 찾아서 넣어준다.
    // 이렇게 객체의존관계를 외부에서 넣어주는 것을 의존성 주입
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

}
