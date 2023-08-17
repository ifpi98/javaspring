package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SingletonServiceTest {

        @Test
        public void ssTest1(){
            SingletonService ss1 = SingletonService.getInstance();
            SingletonService ss2 = SingletonService.getInstance();

            ss1.logic();
            ss2.logic();

            Assertions.assertThat(ss1).isEqualTo(ss2);
        }







}
