package com.cribbstechnologies.clients.mandrill.model.response.message;

/**
 * User: telrod
 * Date: 4/11/14
 */
public class MessageInfoClicksDetail {
    Integer ts;
    String ip;
    String location;
    String ua;
    String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MessageInfoClicksDetail{" +
                "ts=" + ts +
                ", ip='" + ip + '\'' +
                ", location='" + location + '\'' +
                ", ua='" + ua + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
