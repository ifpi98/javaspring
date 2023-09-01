package hello.login.web;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home(Model model) {
        return "/home";
    }


    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

        if(memberId == null){
            return "home";
        }

        // 로그인
        Member loginMember = memberRepository.findById(memberId);
        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";

    }

    public String homeLoginV2(HttpServletRequest request, Model model) {

        Member member = (Member)sessionManager.getSession(request);
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";

    }

    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";

    }

    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";

    }

}