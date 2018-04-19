package me.wiz3ard.uptimeapp.ping;

import me.wiz3ard.uptimeapp.utils.IntegerUtils;
import me.wiz3ard.uptimeapp.utils.Units;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

@Service
public class HttpPingService implements PingService {

    private final int TIMEOUT = Units.SECOND * 10;

    @Override
    public PingResponse pingAddress(String address) {

        try {
            URL siteURL = new URL(address);
           HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setConnectTimeout(TIMEOUT);
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            switch (IntegerUtils.getFirstInteger(code)){
                case 2:
                     return PingResponse.UP;
                case 3:
                    return PingResponse.UP;
                case 4:
                    return PingResponse.ERROR;
                case 5:
                    return PingResponse.ERROR;
                default:
                    return PingResponse.DOWN;

            }


        }catch (UnknownHostException e){
            return PingResponse.UNKNOWN_HOST;
        }
        catch (IOException e) {
            return PingResponse.ERROR;
        }

    }
}
