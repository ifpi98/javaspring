package hello.springmvc.basic;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

// @Data -> @Getter @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor 를 자동 적용


@Data
public class HelloData {
    private String username;
    private int age;
}
