package me.wiz3ard.uptimeapp.Server.dto;


import me.wiz3ard.uptimeapp.Server.ServerUptimeLog;

import java.util.List;

public final class ServerModelDto {
    private final String ID;
    private final String address;
    private final String description;
    private final List<ServerUptimeLog> uptimeLogList;

    public ServerModelDto(String ID, String address, String description, List<ServerUptimeLog> uptimeLogList) {
        this.ID = ID;
        this.address = address;
        this.description = description;
        this.uptimeLogList = uptimeLogList;
    }

    public String getID() {
        return ID;
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
