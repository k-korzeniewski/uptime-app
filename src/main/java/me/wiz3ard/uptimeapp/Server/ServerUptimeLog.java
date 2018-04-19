package me.wiz3ard.uptimeapp.Server;

import me.wiz3ard.uptimeapp.ping.PingResponse;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public final class ServerUptimeLog {
    @NotNull @NotEmpty
    private final LocalDateTime  date;

    private final PingResponse pingResponse;

    private ServerUptimeLog(LocalDateTime date, PingResponse pingResponse){
        this.date = date;
        this.pingResponse = pingResponse;
    }

    public static ServerUptimeLog createNow(PingResponse response){
        return new ServerUptimeLog(LocalDateTime.now(),response);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public PingResponse getPingResponse() {
        return pingResponse;
    }


}
