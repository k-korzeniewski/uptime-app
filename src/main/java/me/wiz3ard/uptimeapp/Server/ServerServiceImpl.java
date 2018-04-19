package me.wiz3ard.uptimeapp.Server;

import me.wiz3ard.uptimeapp.ping.PingResponse;
import me.wiz3ard.uptimeapp.utils.BadRequestParamException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    private ServerRepository serverRepository;


    @Override
    public Optional<List<ServerModel>> getAllServers() {
        return Optional.of(serverRepository.findAll());
    }

    @Override
    public Optional<List<ServerModel>> getAllServers(Optional<String> sort) {
        if(sort.isPresent()) {
            String[] sortParted = sort.get().split(",");
            if (sortParted.length != 2)
                throw new BadRequestParamException();
            return Optional.of(serverRepository.findAll(new Sort(Sort.Direction.fromString(sortParted[1]), sortParted[0])));
        }
        return Optional.empty();
    }



    @Override
    public Optional<List<ServerModel>> getAllServers(Optional<Integer> page, Optional<Integer> perPage) {
        if(page.isPresent()&& perPage.isPresent()) {
            return Optional.of(serverRepository.findAll(PageRequest.of(page.get(), perPage.get())).getContent());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ServerModel>> getAllServers(Optional<String> sort, Optional<Integer> page, Optional<Integer> perPage) {
        if(sort.isPresent() && page.isPresent() && perPage.isPresent()) {
            String[] sortParted = sort.get().split(",");
            if (sortParted.length != 2)
                throw new BadRequestParamException();
            return Optional.of(serverRepository
                    .findAll(PageRequest.of(page.get(), perPage.get(), new Sort(Sort.Direction.fromString(sortParted[1]), sortParted[0]))).getContent());
        }
        return Optional.empty();
    }

    @Override
    public void addServerUptimeLog(ServerModel server, ServerUptimeLog serverUptimeLog) {

        serverRepository.findById(server.get_id())
                .ifPresentOrElse(s -> serverRepository.save(new ServerModel(s.get_id(), s.getAddress(), s.getDescription(),
                        Stream.concat(List.of(serverUptimeLog).stream(), s.getUptimeLogList().stream()).collect(Collectors.toList()))),
                        () -> {
                    List<ServerUptimeLog> serverUptimeLogList = Stream.concat(server.getUptimeLogList().stream(),
                            List.of(serverUptimeLog).stream()).collect(Collectors.toList());
                    serverRepository.save(new ServerModel(server.get_id(),server.getAddress(),server.getDescription(),serverUptimeLogList));
                });
    }

    @Override
    public float calculateUptimePercent(ServerModel server) {
        float upCount = server.getUptimeLogList().stream().filter(s -> s.getPingResponse() == PingResponse.UP).count();
        float allCount = server.getUptimeLogList().size();
        return (upCount/allCount) * 100;
    }

    @Override
    public ServerModel getServerModelFromId(String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return serverRepository.findById(objectId).orElseThrow(ServerNotExistException::new);
        }catch (IllegalArgumentException e){
            throw new ServerNotExistException();
        }
    }
}
