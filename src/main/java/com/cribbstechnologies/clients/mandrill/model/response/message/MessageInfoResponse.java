package com.cribbstechnologies.clients.mandrill.model.response.message;

import com.cribbstechnologies.clients.mandrill.model.response.BaseMandrillResponse;

import java.util.List;

/**
 * User: telrod
 * Date: 4/10/14
 */
public class MessageInfoResponse extends BaseMandrillResponse {

    Integer ts;
    String _id;
    String sender;
    String template;
    String subject;
    String email;
    Integer opens;
    Integer clicks;
    String state;
    List<String> tags;
    List<MessageInfoSmtpEvents> smtp_events;
    List<MessageInfoOpensDetail> opens_detail;
    List<MessageInfoClicksDetail> clicks_detail;
    List<String> resends;
    String bgtools_code;
    String diag;
    String bounce_description;

    public List<String> getResends() {
        return resends;
    }

    public void setResends(List<String> resends) {
        this.resends = resends;
    }

    public List<MessageInfoSmtpEvents> getSmtp_events() {
        return smtp_events;
    }

    public void setSmtp_events(List<MessageInfoSmtpEvents> smtp_events) {
        this.smtp_events = smtp_events;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOpens() {
        return opens;
    }

    public void setOpens(Integer opens) {
        this.opens = opens;
    }

    public Integer getClicks() {
        return clicks;
    }

    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<MessageInfoOpensDetail> getOpens_detail() {
        return opens_detail;
    }

    public void setOpens_detail(List<MessageInfoOpensDetail> opens_detail) {
        this.opens_detail = opens_detail;
    }

    public List<MessageInfoClicksDetail> getClicks_detail() {
        return clicks_detail;
    }

    public void setClicks_detail(List<MessageInfoClicksDetail> clicks_detail) {
        this.clicks_detail = clicks_detail;
    }

    public String getBgtools_code() {
        return bgtools_code;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getBounce_description() {
        return bounce_description;
    }

    public void setBounce_description(String bounce_description) {
        this.bounce_description = bounce_description;
    }

    public void setBgtools_code(String bgtools_code) {
        this.bgtools_code = bgtools_code;


    }

    @Override
    public String toString() {
        return "MessageInfoResponse{" +
                "ts=" + ts +
                ", _id='" + _id + '\'' +
                ", sender='" + sender + '\'' +
                ", template='" + template + '\'' +
                ", subject='" + subject + '\'' +
                ", email='" + email + '\'' +
                ", opens=" + opens +
                ", clicks=" + clicks +
                ", state='" + state + '\'' +
                ", tags=" + tags +
                ", smtp_events=" + smtp_events +
                ", opens_detail=" + opens_detail +
                ", clicks_detail=" + clicks_detail +
                ", resends=" + resends +
                '}';
    }

}
