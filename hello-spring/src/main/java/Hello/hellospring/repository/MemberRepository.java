package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

    // Optional로 처리하게 되면 null로 발생하는 예외상황을 처리하기가 수월함
    // Java8에서는 Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와준다.
    // Optional<T>는 null이 올 수 있는 값을 감싸는 wrapper 클래스로
    // 참조하더라도 NullPointException이 발생하지 않도록 도와준다.
    // Optional 클래스는 value에 값을 저장하기 때문에 값이 null이더라도 바로 NPE가 발생하지 않으며
    // 클래스이기때문에 각종 메소드를 제공해준다.
    
}
