package me.wiz3ard.uptimeapp.scheduler;

import me.wiz3ard.uptimeapp.utils.Units;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class PingSchedulerImpl implements PingScheduler{

    private final int DELAY = Units.SECOND * 60;


    private PingSchedulerTask pingSchedulerTask;


    public PingSchedulerImpl(PingAllSchedulerTask allSchedulerTask){
        setPingSchedulerTask(allSchedulerTask);
    }

    public void setPingSchedulerTask(PingSchedulerTask schedulerTask){
        this.pingSchedulerTask = schedulerTask;
    }
    @Scheduled(fixedDelay = DELAY)
    public void ping(){
        pingSchedulerTask.ping();
    }


}
