package me.wiz3ard.uptimeapp.utils;

import me.wiz3ard.uptimeapp.Server.ServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerDbData implements CommandLineRunner {

    private final ServerRepository serverRepository;

    Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CommandLineRunnerDbData(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override
    public void run(String... args) {



//        List.of(
//                new ServerModel(new ObjectId(),"http://www.google.pl","Google ;)",List.of()),
//                new ServerModel(new ObjectId(),"http://www.onet.pl","Onet ;(",List.of()),
//                new ServerModel(new ObjectId(),"http://www.stackoverflow.pl","Stackoverflow",List.of()),
//                new ServerModel(new ObjectId(),"someunknownhost","unknown...",List.of())
//        ).stream().forEach(s->{
//            serverRepository.save(s);
//            LOG.info("Saved: {}",s.getAddress());
//        });

    }
}
