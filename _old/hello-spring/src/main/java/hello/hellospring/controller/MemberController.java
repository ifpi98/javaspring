package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";

    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @GetMapping(value = "/members/delete")
    public String deleteForm() {
        return "members/deleteMemberForm";
    }

    @PostMapping(value = "/members/delete")
    public String delete(DeleteForm form) {
        Optional<Member> member = Optional.of(new Member());
        member = memberService.findOne(form.getId());
//        System.out.println("1" + form.getId());

        if (member.isEmpty()) {
            System.out.println("cannot find id");
            return "redirect:/";
        } else {

            memberRepository.delete(form.getId());
//            System.out.println("2" + form.getId());
        }

        return "redirect:/";

    }

    @GetMapping(value = "/members/update")
    public String updateForm() {
        return "members/updateMemberForm";
    }

    @PostMapping(value = "/members/update")
    public String update(UpdateForm form) {
        Optional<Member> member = Optional.of(new Member());
        member = memberService.findOne(form.getId());
//        System.out.println(member);
//        System.out.println("1" + form.getId());

        if (member.isEmpty()) {
            System.out.println("cannot find id");
            return "redirect:/";
        } else {

            memberRepository.update(form.getId(), form.getName());
//            System.out.println("2" + form.getId());
        }

        return "redirect:/";

    }

}
