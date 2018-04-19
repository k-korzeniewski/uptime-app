package me.wiz3ard.uptimeapp.Server;

import java.util.List;
import java.util.Optional;

public interface ServerService {
     Optional<List<ServerModel>> getAllServers();
     Optional<List<ServerModel>> getAllServers(Optional<String> sort);
     Optional<List<ServerModel>> getAllServers(Optional<Integer> page,Optional<Integer> perPage);
     Optional<List<ServerModel>> getAllServers(Optional<String> sort, Optional<Integer> page, Optional<Integer> perPage);
     void addServerUptimeLog(ServerModel server,ServerUptimeLog serverUptimeLog);
     float calculateUptimePercent(ServerModel server);
     ServerModel getServerModelFromId(String id);
}
