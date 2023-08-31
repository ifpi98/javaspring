package hello.login.web.member;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Member {

    private Long id;

    @NotBlank
    private String loginId;

//    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public Member() {
    }

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
