package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    // 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회 X

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");

    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";

    }

    //@RequestParam 사용
    //HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";

    }

    //@RequestParam 사용
    //String, int 등의 단순 타입이면 @RequestParam도 생략가능
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";

    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // username(String)은 필수
    // age(Integer)는 선택
    // int의 경우 null을 받을 수 없어서 에러발생할 수 있음 (그래서 Integer 사용)

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 파라미터를 Map으로 조회하기 - requestParamMap
//    @RequestParam Map => Map(key=value)
//    @RequestParam MultiValueMap => MultiValueMap(key=[value1, value2, ...] ex)
//        (key=userIds, value=[id1, id2])

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    // http://localhost:8080/request-param-map?username=userA,userB&age=20,25

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // http://localhost:8080/model-attribute-v1?username=userA&age=20
    // 스프링 MVC 는 @ModelAttribute가 있으면 다음을 실행한다.
    // HelloData객체를 생성한다.
    // 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을
    //입력(바인딩)한다.HelloData
    // 예) 파라미터 이름이 username이면 setUsername() 메서드를 찾는다.

    // @ModelAttribute 생략
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    // http://localhost:8080/model-attribute-v1?username=userB&age=25
    // 스프링은 해당 생략시 다음과 같은 규칙을 적용한다.
    // String, int, Integer같은 단순 타입 = @RequestParam
    // 나머지 = @ModelAttribute (argument resolver로 지정해둔 타입 외)


}
