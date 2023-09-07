package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//JdbcTemplate

@Slf4j
@Repository
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {

    private final JdbcTemplate template;

    public JdbcTemplateItemRepositoryV1(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item (item_name, price, quantity) values (?, ?, ?)";
        //GeneratedKeyHolder 객체를 생성한다. 이 클래스는 자동 생성된 키 값을 구해주는 KeyHolder 구현 클래스이다.
        KeyHolder keyHolder = new GeneratedKeyHolder();
        //update() 메서드는 PreparedStatementCreator 객체와 KeyHolder 객체를 파라미터로 갖는다.
        template.update(connection -> {
            //자동 증가 키
            //PreparedStatement 객체를 생성하는데 두 번째 파라미터로 String 배열인 ["ID"]를 주었다. 이 두번째 파라미터는 자동 생성되는 키 칼럼 목록을 지정할 때 사용한다. MEMBER 테이블은 ID 칼럼이 auto_increment 칼럼이므로 두번째 파라미터 값으로 ID를 준다.
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            return ps;
        },keyHolder);
        //KeyHolder에 보관된 값은 getKey() 메서드를 이용해 구할 수 있다.
        long key = keyHolder.getKey().longValue();
        item.setId(key);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item set item_name=?, price=?, quantity=? where id=?";
        //?에 바인딩할 파라미터를 순서대로 전달하면된다
        template.update(sql,
                updateParam.getItemName(),
                updateParam.getPrice(),
                updateParam.getQuantity(),
                itemId);
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id=?";
        try {
            Item item = template.queryForObject(sql, itemRowMapper(), id);
            return Optional.of(item);  //null이 아닌 명시된 값을 가지는 Optional 객체를 반환
        }catch(EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    //RowMapper를 사용하면, 원하는 형태의 결과값을 반환할 수 있다. item객체 자체를 반환받을 수 있다.
    private RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setItemName(rs.getString("item_name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        };
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {

        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        String sql = "select id, item_name, price, quantity from item";

        //동적쿼리
        //select id, item_name, price, quantity from item
        //where item_name like concat('%',?,'%')
        // %는 모든 문자, _는 한글자
        if(StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false;
        List<Object> param = new ArrayList<>();

        if(StringUtils.hasText(itemName)){
            sql += " item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }
        log.info("sql={}", sql);
        return template.query(sql, itemRowMapper(), param.toArray());
    }
}
