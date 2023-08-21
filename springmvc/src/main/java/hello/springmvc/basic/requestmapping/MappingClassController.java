package hello.springmvc.basic.requestmapping;

// 회원 목록 조회 get /users
// 회원 등록 post /users
// 회원 조회 get /users/{userId}
// 회원 수정 patch /users/{userId}
// 회원 삭제 delete /users/{userId}

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

        @GetMapping
        public String users(){
            return "get users";
        }

        @PostMapping
        public String postUsers(){
            return "post users";
        }

        @GetMapping("/{userId}")
        public String findUser(@PathVariable String userId){
            return "get userId=" + userId;
        }

        @PatchMapping("/{userId}")
        public String userUpdate(@PathVariable String userId){
            return "update userId=" + userId;
        }

        @DeleteMapping("/{userId}")
        public String deleteUser(@PathVariable String userId){
            return "delete userId=" + userId;
        }

}
