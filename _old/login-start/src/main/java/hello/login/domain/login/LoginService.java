package hello.login.domain.login;

import hello.login.web.member.Member;
import hello.login.web.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

//    public LoginService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public Member login(String loginId, String password){
        return memberRepository.findByLoginId(loginId).filter(m -> m.getPassword().
                equals(password)).orElse(null);
    }

    public boolean loginCheck(String id, String password){
        List<Member> memberList = memberRepository.findAll();
        for(Member member : memberList){
            if (id.equals(member.getLoginId()) && password.equals(member.getPassword())){
                System.out.println("Found it");
                return true;
            }
//            System.out.println("1 - " + id);
//            System.out.println("2 - " + member.getLoginId());
//            System.out.println("3 - " + password);
//            System.out.println("4 - " + member.getPassword());

        }

        System.out.println("can`t find");
        return false;
    }

}
