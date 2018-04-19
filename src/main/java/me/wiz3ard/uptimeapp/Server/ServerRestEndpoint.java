package me.wiz3ard.uptimeapp.Server;

import me.wiz3ard.uptimeapp.Server.dto.ServerModelConventer;
import me.wiz3ard.uptimeapp.Server.dto.ServerModelDto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:3000")
public class ServerRestEndpoint {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerService serverService;

    @Autowired
    private ServerModelConventer serverModelConventer;

    @GetMapping()
    public Collection<ServerModelDto> getAllServers(@RequestParam(value = "page",required = false) Optional<Integer> page,
                                                    @RequestParam(value = "sizePerPage", required = false) Optional<Integer> sizePerPage,
                                                    @RequestParam(value="sort",required = false) Optional<String> sort){


            Optional<List<ServerModel>> serverModels = Stream.<Supplier<Optional<List<ServerModel>>>>of(
                    () -> serverService.getAllServers(sort, page, sizePerPage),
                    () -> serverService.getAllServers(page, sizePerPage),
                    () -> serverService.getAllServers(sort),
                    () -> serverService.getAllServers()
            ).map(Supplier::get).filter(Optional::isPresent).map(Optional::get).findFirst();
            return serverModels.get().stream().map(s -> serverModelConventer.convertTo(s)).collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public ServerModelDto getServer(@PathVariable(value="id") String id){

        return serverModelConventer.convertTo(serverService.getServerModelFromId(id));
    }
    @PutMapping
    public ResponseEntity<?> addServer(@Valid @RequestBody ServerModelDto server){
        serverRepository.save(serverModelConventer.convertFrom(server));
        return new ResponseEntity(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteServer(@PathVariable(value="id") String id){
        serverRepository.deleteById(new ObjectId(id));
        return new ResponseEntity(HttpStatus.OK);
    }


}
