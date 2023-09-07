package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

// @콘트롤러는 반환 값이 String이면 뷰 이름으로 인식된다. 가\

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-vi", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    //PathVariable(경로 변수)사용
    // 변수명이 같으면 생략가능

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    // http://localhost:8080/mapping/userA

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }

    @GetMapping(value="/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    //http://localhost:8080/mapping-param?mode=debug

    // 특정 헤더 조건 매핑
    // headers="mode"
    // headers="!mode"
    // headers="mode=debug"
    // headers="mode!=debug"

    @GetMapping(value="/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    //http://localhost:8080/mapping-header?mode=debug

    @PostMapping(value="/mapping-consume", consumes="application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    //http://localhost:8080/mapping-consume

    //미디어 타입 조건 매핑 - HTTP 요청 Accept, produce

    // produce = "text/html"
    // produce = "!text/html"
    // produce = "text/*"
    // produce = "*\/*"

    @PostMapping(value="/mapping-produce", produces="text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

//    예시)
//    produces =MediaType.TEXT_PLAIN_VALUE
//    produces = "text/plain;charset=UTF-8"
//    localhost:8080/mapping-produce



}
