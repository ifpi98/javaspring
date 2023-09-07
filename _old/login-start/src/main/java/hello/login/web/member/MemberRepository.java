package hello.login.web.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> site = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        site.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return site.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream().filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }
    // Optional을 감싸주는 것으로 NullPointerException이 발생하지 않도록 한다.
    // 해당 로그인Id가 없으면 null이 반환되므로...
    // 아래의 식이 너무 길어서 위의 람다형식으로 표현

    public Optional<Member> findByLoginId2(String loginId) {
        List<Member> all = findAll();
        for (Member member : all) {
            if (member.getLoginId().equals(loginId)) {
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }

    public List<Member> findAll() {
        return new ArrayList<>(site.values());
    }

    public void update(Long memberId, Member updateParam) {
        Member findMember = findById(memberId);
        findMember.setLoginId(updateParam.getLoginId());
        findMember.setPassword(updateParam.getPassword());
        findMember.setName(updateParam.getName());
    }

    public void clearStore() {
        site.clear();
    }


}
