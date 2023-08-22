package hello.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.Servlet;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    //localhost:8080/request-body-string-v1

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    // 스프링 MVC는 다음 파라미터를 지원한다.
    // HttpEntity: HTTP header, body 정보를 편리하게 조회 : 메시지 바디 정보를 직접 조회
    // HttpEntity는 응답에도 사용 가능, 메시지 바디 정보 직접 반환, 헤더 정보 포함 가능, view 조회 X
    // RequestEntity : HttpMethod, url 정보가 추가, 요청에서 사용
    // ResponseEntity : HTTP 상태 코드 설정 가능, 응답에서 사용

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok");
    }

//    @RequestBody - requestBodyStringV4
//    @RequestBody : 메시지 바디 정보를 직접 조회(@RequestParam X, @ModelAttribute X)
//    HttpMessageConverter 사옹 -> StringHttpMessageConverter 적용
//    @ResponseBody : 메시지 바디 정보 직접 반환 (view 조회X)

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);
        return "ok";
    }





}
