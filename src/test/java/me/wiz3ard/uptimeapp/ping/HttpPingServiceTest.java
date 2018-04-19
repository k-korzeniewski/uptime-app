package me.wiz3ard.uptimeapp.ping;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpPingServiceTest{

    @Autowired
    HttpPingService httpPingService;

    @Test
    public void pingAddressTestShouldGiveUp(){
        assertThat(httpPingService.pingAddress("http://www.google.com")).isEqualTo(PingResponse.UP);
    }
    @Test
    public void pingAddressTestShouldGiveUnknownHost(){
        assertThat(httpPingService.pingAddress("http://wrongaddress")).isEqualTo(PingResponse.UNKNOWN_HOST);
    }

}
