package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //설계변경으로 MemberServiceImpl 은 MemoryMemberRepository를 의존하지않는다!
    //단지 MemberRepository 인터페이스만의존한다.
    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //MemberServiceImpl 입장에서 생성자를 통해 어떤 구현객체가 들어올지(주입될지)는알 수없다.
    //MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직외부( AppConfig )에서결정 된다. => 생성자 주입
    //MemberServiceImpl 은 이제부터 의존관계에 대한고민은 외부에 맡기고 실행에만 집중하면 된다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
