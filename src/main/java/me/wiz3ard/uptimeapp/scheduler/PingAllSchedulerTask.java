package me.wiz3ard.uptimeapp.scheduler;

import me.wiz3ard.uptimeapp.Server.ServerRepository;
import me.wiz3ard.uptimeapp.Server.ServerService;
import me.wiz3ard.uptimeapp.Server.ServerUptimeLog;
import me.wiz3ard.uptimeapp.ping.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class PingAllSchedulerTask implements PingSchedulerTask {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private PingService pingService;

    @Autowired
    private ServerService serverService;


    @Override
    public void ping() {
        ExecutorService executor = Executors.newWorkStealingPool();
        serverRepository.findAll().forEach(s -> CompletableFuture
                .supplyAsync(()->pingService.pingAddress(s.getAddress()),executor)
                .thenAccept(r -> {
                    float startTime = System.nanoTime();
                    ServerUptimeLog serverUptimeLog = ServerUptimeLog.createNow(r);
                    serverService.addServerUptimeLog(s, serverUptimeLog);
                    float endTime = System.nanoTime();
                    log.info("Address: {}, thread : {}  ===== IN {} MS", s.getAddress(), Thread.currentThread().getName(),
                            Math.round((endTime-startTime))/1000000);
                }));
    }
}
