package me.wiz3ard.uptimeapp.Server;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public final class ServerModel {

    @Indexed()
    private final ObjectId _id;
    @NotNull @NotBlank @NotEmpty
    private final String address;
    @NotNull @NotBlank @NotEmpty
    private final String description;
    @NotNull @NotBlank @NotEmpty
    private final List<ServerUptimeLog> uptimeLogList;
    public ServerModel(ObjectId _id, String address, String description, List<ServerUptimeLog> uptimeLogList) {
        this._id = _id;
        this.address = address;
        this.description = description;
        this.uptimeLogList = uptimeLogList;
    }

    public ObjectId get_id() {
        return _id;
    }


    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public List<ServerUptimeLog> getUptimeLogList() {
        return uptimeLogList;
    }
}

