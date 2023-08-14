package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    // 설계 변경을 통해 MemoryMemberRepository를 의존하지 않는다.
    // MemberServiceImpl 입장에서 생성자를 통해 어떤 구현객체가 들어올지 알 수 없음.
    // AppConfig가 어떤 값을 넣어주느냐에 달려 있음. (외부에서 결정)
    // 의존 관계에 더이상 신경쓸 것 없이 자신의 기능에만 집중할 수 있음.
    // 생성자 주입, 제어의 역전

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
