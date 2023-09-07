package hello.login.web.login;

import hello.login.domain.login.LoginForm;
import hello.login.domain.login.LoginService;
import hello.login.web.SessionConst;
import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
//@RequestMapping("/web")
public class LoginController {

    private final MemberRepository memberRepository;
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String login(@ModelAttribute("loginForm") LoginForm form) {
        return "/login/loginForm";
    }


    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){

        if(bindingResult.hasErrors() ){
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        final LoginService loginService = new LoginService(memberRepository);

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);

        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }

    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){

        if(bindingResult.hasErrors() ){
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        final LoginService loginService = new LoginService(memberRepository);

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);

        sessionManager.createSession(loginMember, response);
        return "redirect:/";
    }


    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){

        if(bindingResult.hasErrors() ){
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        final LoginService loginService = new LoginService(memberRepository);

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);

        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";

        // 세션에 데이터를 보관하는 방법은 request.setAttribute(..)와  비슷하다.
        // 하나의 세션에 여러 값을 저장할 수 있다.

        // 세션의 create 옵션
        // request.getSession(true)
        // 세션이 있으면 기존 세션을 반환한다.
        // 세션이 없으면 새로운 세션을 생성해서 반환한다.
        // request.getSession(false)
        // 세션이 있으면 기존 세션을 반환한다.
        // 세션이 없으면 새로운 세션을 생성하지 않는다. null을 반환한다.



    }

    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request){

        if(bindingResult.hasErrors() ){
            log.info("errors = {}", bindingResult);
            return "/login/loginForm";
        }

        final LoginService loginService = new LoginService(memberRepository);

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
        log.info("login? {}", loginMember);

        if (loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        response.addCookie(idCookie);

        // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
        HttpSession session = request.getSession();
        // 세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:" + redirectURL;

        // 세션에 데이터를 보관하는 방법은 request.setAttribute(..)와  비슷하다.
        // 하나의 세션에 여러 값을 저장할 수 있다.

        // 세션의 create 옵션
        // request.getSession(true)
        // 세션이 있으면 기존 세션을 반환한다.
        // 세션이 없으면 새로운 세션을 생성해서 반환한다.
        // request.getSession(false)
        // 세션이 있으면 기존 세션을 반환한다.
        // 세션이 없으면 새로운 세션을 생성하지 않는다. null을 반환한다.



    }

    public String logout(HttpServletResponse response){
        expireCookie(response, "memberId");
        return "redirect:/";

    }

    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";

    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }

        return "redirect:/";

    }


    private void expireCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
