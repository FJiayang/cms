package com.fjy.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "serverproperties")
public class ServerProperties {
    private String portNum;

    public String getPortNum() {
        return portNum;
    }

    public void setPortNum(String portNum) {
        this.portNum = portNum;
    }
}
