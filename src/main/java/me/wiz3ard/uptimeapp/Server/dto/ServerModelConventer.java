package me.wiz3ard.uptimeapp.Server.dto;

import me.wiz3ard.uptimeapp.Server.ServerModel;
import me.wiz3ard.uptimeapp.utils.ModelConventer;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ServerModelConventer implements ModelConventer<ServerModel,ServerModelDto> {
    @Override
    public ServerModel convertFrom(ServerModelDto serverModelDto) {
        return new ServerModel(new ObjectId(serverModelDto.getID()),serverModelDto.getAddress(),
                serverModelDto.getDescription(),serverModelDto.getUptimeLogList());
    }

    @Override
    public ServerModelDto convertTo(ServerModel serverModel) {
        return new ServerModelDto(serverModel.get_id().toHexString(),serverModel.getAddress(),
                serverModel.getDescription(),serverModel.getUptimeLogList());
    }

}
