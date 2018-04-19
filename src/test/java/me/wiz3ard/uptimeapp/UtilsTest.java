package me.wiz3ard.uptimeapp;

import me.wiz3ard.uptimeapp.utils.IntegerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsTest {
    @Test
    public void getFirstIntegerTest(){
        assertThat(IntegerUtils.getFirstInteger(97523)).isEqualTo(9);
    }
}
