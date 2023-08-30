package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import java.lang.reflect.Field;
import java.security.CodeSigner;

public class MessageCodesResolverTest {
    // MessageCodesResolver �����޽����� ������ ����
    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for (String messageCode : messageCodes){
            System.out.println("messageCode");
        }
        Assertions.assertThat(messageCodes).containsExactly("required.item","required");
    }

//    Assertions.assertThat ���Ŀ� ����ϴ� contains �޼ҵ�
//    �ߺ�����, ������ ���� ���� ���� ��ġ�ϸ� �׽�Ʈ�� ����
//    containsOnly : ���� �ߺ��� �����ϴ� ��� ���Ұ��� ������ ��Ȯ�� ��ġ
//    containsExactly: ������ �����ؼ� ��Ȯ�� ��ġ

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

    // DefaultMessageCodesResolver�� �⺻ �޽��� ���� ��Ģ
//    ��ü ������ ��� ���� ������ 2���� ����
//            1. code + "." + Object name
//            2. code

    // �ʵ� ������ ��� ���� ������ 4���� �޽��� �ڵ� ����
//    1. code + "." + object name + "." + field
//    2. code + "." + field
//    3. code + "." + field type
//    4. code

    // ���� �޽��� ���
//    Ÿ�Ӹ��� ȭ���� �������� �� Throwable:errors�� ����ȴ�. ���� �� �� ������ �ִٸ� ������ ���� �޽��� �ڵ带
//    ������� ���ư��鼭 �޽����� ã�´�.Field �׸��� ������ ����Ʈ �޽����� ����Ѵ�.




}
