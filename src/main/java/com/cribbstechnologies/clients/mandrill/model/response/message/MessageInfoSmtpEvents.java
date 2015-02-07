package com.cribbstechnologies.clients.mandrill.model.response.message;

/**
 * User: telrod
 * Date: 4/11/14
 */
public class MessageInfoSmtpEvents {
    Integer ts;
    String type;
    String diag;
    String source_ip;
    String destination_ip;
    Integer size;

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getSource_ip() {
        return source_ip;
    }

    public void setSource_ip(String source_ip) {
        this.source_ip = source_ip;
    }

    public String getDestination_ip() {
        return destination_ip;
    }

    public void setDestination_ip(String destination_ip) {
        this.destination_ip = destination_ip;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "MessageInfoSmtpEvents{" +
                "ts=" + ts +
                ", type='" + type + '\'' +
                ", diag='" + diag + '\'' +
                ", source_ip='" + source_ip + '\'' +
                ", destination_ip='" + destination_ip + '\'' +
                ", size=" + size +
                '}';
    }
}
