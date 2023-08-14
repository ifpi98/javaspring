package hello.core.member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    //회원 가입
    void join(Member member);

    //회원 조회
//    public List<Member> findMembers();
    Member findMember(Long memberId);

}
