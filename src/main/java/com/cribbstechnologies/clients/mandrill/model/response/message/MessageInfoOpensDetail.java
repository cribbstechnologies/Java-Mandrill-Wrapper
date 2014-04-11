package com.cribbstechnologies.clients.mandrill.model.response.message;

/**
 * User: telrod
 * Date: 4/11/14
 */
public class MessageInfoOpensDetail {
    Integer ts;
    String ip;
    String location;
    String ua;

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    @Override
    public String toString() {
        return "MessageInfoOpensDetail{" +
                "ts=" + ts +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", ua='" + ua + '\'' +
                '}';
    }
}
