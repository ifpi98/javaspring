package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {
// @RequestBody�� HTTP Body�� �����͸� ��ü�� ��ȯ�� �� ����Ѵ�. �ַ� API JSON ��û�� �ٷ� �� ����Ѵ�.
// API�� ���, 3���� ��츦 ������ �����ؾ� �Ѵ�.
    // ���� ��û: ����
    // ���� ��û: JSON�� ��ü�� �����ϴ� �� ��ü�� ������ => Controller ȣ�� ����
    // ���� ���� ����: JSON�� ��ü�� �����ϴ� ���� ����, �������� ������. => �������� �߻�

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){
        log.info("API ��Ʈ�ѷ� ȣ��");
        if (bindingResult.hasErrors()) {
            log.info("���� ���� �߻� errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("���� ���� ����");
        return form;
    }
}
