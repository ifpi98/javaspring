package hello.login.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Slf4j
@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        return "/home";
    }

    @GetMapping("/members/add")
    public String addMember(Model model) {
        return "/members/addMemberForm";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "/login";
    }
}