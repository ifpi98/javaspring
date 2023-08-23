package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    // JtbcTemplate는 DataSource를 필요로 한다.
    // 여기선 application.properties에 등록한 DataSource가 쓰인다.
    // DataSource는 스프링 컨테이너에서 자동으로 BEAN으로 등록시켜주기 때문에 @Autowired로 받기만 하면 된다.

    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // 기본 생성자
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        // withTableName() : 추가할 테이블의 이름을 설정
        // usingGeneratedKeyColumns(): 자동 생성되는 열의 이름을 설정
        // AUTO_INCREMENT되는 열의 이름을 작성하면 됨!
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        // executeAndReturnKey(): DB에 데이터를 추가 + 자동 생성된 key값을 반환
        // AUTO_INCREMENT 열을 가져올 수 있음
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //JdbcTemplate에서는 select 쿼리문을 다를 시 'query() 메서드'를 사용한다.
        // 해당 메서드의 반환 타입은 List타입이다.
        // query() 메서드의 첫번째 인자 값으로는  sql문이 들어가고,
        // 두번째 인자 값으로는 RowMapper가 들어간다.
        // RowMapper는 ResultSet결과를 자바 객체로 변환해주는 것이다.
        // 3번째 인자 값부터는 쿼리문에서 ?에 해당하는 값이 들어간다.

        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(),id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(),name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    // RowMapper 인터페이스 사용하면 , 원하는 형태의 결과값을 반환할 수 있다.
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
