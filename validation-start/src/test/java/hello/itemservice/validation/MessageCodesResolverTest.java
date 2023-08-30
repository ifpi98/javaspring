package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import java.lang.reflect.Field;
import java.security.CodeSigner;

public class MessageCodesResolverTest {
    // MessageCodesResolver 에러메시지를 가지고 있음
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes){
            System.out.println("messageCode");
        }
        Assertions.assertThat(messageCodes).containsExactly("required.item","required");
    }

//    Assertions.assertThat 이후에 사용하는 contains 메소드
//    중복여부, 순서에 관계 없이 값만 일치하면 테스트가 성공
//    containsOnly : 순서 중복을 무시하는 대신 원소값과 갯수가 정확히 일치
//    containsExactly: 순서를 포함해서 정확히 일치

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for (String messageCode : messageCodes){
            System.out.println("messageCode");
        }
        Assertions.assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required");
    }

    // DefaultMessageCodesResolver의 기본 메시지 생성 규칙
//    객체 오류의 경우 다음 순서로 2가지 생성
//            1. code + "." + Object name
//            2. code

    // 필드 오류의 경우 다음 순서로 4가지 메시지 코드 생성
//    1. code + "." + object name + "." + field
//    2. code + "." + field
//    3. code + "." + field type
//    4. code

    // 오류 메시지 출력
//    타임리프 화면을 렌더링할 때 Throwable:errors가 실행된다. 만약 이 때 오류가 있다면 생성된 오류 메시지 코드를
//    순서대로 돌아가면서 메시지를 찾는다.Field 그리고 없으면 디폴트 메시지를 출력한다.




}
