package hello.login.web.member;

import hello.login.domain.item.Item;
import hello.login.domain.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepository memberRepository;

//    @GetMapping
//    public String members(Model model) {
//        List<Member> members = memberRepository.findAll();
//        model.addAttribute("members", members);
//        return "items/items";
//    }

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member) {
        return "/members/addMemberForm";
    }

    @PostMapping("/add")
    public String addMember(@Valid @ModelAttribute Member member, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "/members/addMemberForm";
        }

        memberRepository.save(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("member") Member member) {
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute Member member, BindingResult bindingResult){

        final LoginService loginService = new LoginService(memberRepository);

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
            return "/login";
        }

        if(!loginService.loginCheck(member.getLoginId(), member.getPassword())){
            log.info("errors = {}", bindingResult);
            return "/login";
        }
        return "redirect:/";
    }


}