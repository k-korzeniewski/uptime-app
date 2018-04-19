package me.wiz3ard.uptimeapp.server;

import me.wiz3ard.uptimeapp.Server.ServerModel;
import me.wiz3ard.uptimeapp.Server.ServerRepository;
import me.wiz3ard.uptimeapp.Server.ServerServiceImpl;
import me.wiz3ard.uptimeapp.Server.ServerUptimeLog;
import me.wiz3ard.uptimeapp.ping.PingResponse;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerServiceTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @InjectMocks
    private ServerServiceImpl serverService = new ServerServiceImpl();

    @Mock
    private ServerRepository serverRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void calculateUptimePercentTest() {
        List<ServerUptimeLog> serverUptimeLogsList = List.of(
                ServerUptimeLog.createNow(PingResponse.UP),
                ServerUptimeLog.createNow(PingResponse.DOWN)
        );
        ServerModel testModel = new ServerModel(new ObjectId(), "Test", "test", serverUptimeLogsList);
        float percent = serverService.calculateUptimePercent(testModel);

        assertThat(percent).isEqualTo(50f);
    }

    @Test
    public void getServerModelFromIdTest() {
        ServerModel testModel = new ServerModel(new ObjectId(), "Test", "test", List.of());
        Mockito.when(serverRepository.findById(testModel.get_id())).thenReturn(Optional.of(testModel));

        assertThat(serverService.getServerModelFromId(testModel.get_id().toHexString())).isEqualTo(testModel);
    }
}
