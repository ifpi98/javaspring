package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
        System.out.println("Store Cleared!");
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result, member);
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Summer");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Autumn");
        repository.save(member2);

        Member result1 = repository.findByName("Summer").get();
        assertThat(result1).isEqualTo(member1);

        Member result2 = repository.findByName("Autumn").get();
        assertThat(result2).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Summer");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Autumn");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);


    }
}
