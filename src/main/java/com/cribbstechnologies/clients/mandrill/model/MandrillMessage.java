package com.cribbstechnologies.clients.mandrill.model;

import java.util.List;
import java.util.Map;

public class MandrillMessage {

	private String text;
	private String subject;
	private String from_email;
	private String from_name;
	private String subaccount;
	private MandrillRecipient[] to;
	private String bcc_address;
	private boolean track_opens=false;
	private boolean track_clicks=false;
	private boolean auto_text=false;
	private boolean url_strip_qs=false;
	private boolean preserve_recipients = false;
	private String[] tags = new String[0];
	private String[] google_analytics_domains = new String[0];
	private String[] google_analytics_campaign = new String[0];
    private List<MergeVar> global_merge_vars;
    List<MessageMergeVars> merge_vars;
    List<MandrillAttachment> attachments;
    private boolean important;
    private boolean auto_html;
    private boolean inline_css;
    private boolean merge;
    private String merge_language;
    private Map<String,Object> metadata;
    private Map<String,Object> recipient_metadata;
	
	private Map<String, String> headers;
	
    public List<MandrillAttachment> getAttachments() {
        return this.attachments;
    }

    public String getFrom_email() {
        return this.from_email;
    }

    public String getFrom_name() {
        return this.from_name;
    }

    public List<MergeVar> getGlobal_merge_vars() {
        return this.global_merge_vars;
    }

    public String[] getGoogle_analytics_campaign() {
        return this.google_analytics_campaign;
    }

    public String[] getGoogle_analytics_domains() {
        return this.google_analytics_domains;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public List<MessageMergeVars> getMerge_vars() {
        return this.merge_vars;
    }

    public String getSubject() {
        return this.subject;
    }

    public String[] getTags() {
        return this.tags;
    }

    public String getText() {
        return this.text;
    }

    public MandrillRecipient[] getTo() {
        return this.to;
    }

	public String getBcc_address() {
		return bcc_address;
	}

    public boolean isAuto_text() {
        return this.auto_text;
    }

    public boolean isTrack_clicks() {
        return this.track_clicks;
    }

    public boolean isTrack_opens() {
        return this.track_opens;
    }

    public boolean isUrl_strip_qs() {
        return this.url_strip_qs;
    }

    public void setAttachments(List<MandrillAttachment> attachments) {
        this.attachments = attachments;
    }

    public void setAuto_text(boolean auto_text) {
        this.auto_text = auto_text;
    }

    public void setFrom_email(String from_email) {
        this.from_email = from_email;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public void setGlobal_merge_vars(List<MergeVar> global_merge_vars) {
        this.global_merge_vars = global_merge_vars;
    }

    public void setGoogle_analytics_campaign(String[] google_analytics_campaign) {
        this.google_analytics_campaign = google_analytics_campaign;
    }

    public void setGoogle_analytics_domains(String[] google_analytics_domains) {
        this.google_analytics_domains = google_analytics_domains;
    }

    public void setHeaders(Map<String, String> struct) {
        this.headers = struct;
    }

    public void setMerge_vars(List<MessageMergeVars> merge_vars) {
        this.merge_vars = merge_vars;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTo(MandrillRecipient[] to) {
        this.to = to;
    }
	
	public void setBcc_address(String bcc) {
		this.bcc_address = bcc;
	}

    public void setTrack_clicks(boolean track_clicks) {
        this.track_clicks = track_clicks;
    }

    public void setTrack_opens(boolean track_opens) {
        this.track_opens = track_opens;
    }

    public void setUrl_strip_qs(boolean url_strip_qs) {
        this.url_strip_qs = url_strip_qs;
    }

    public final boolean isPreserve_recipients() {
        return preserve_recipients;
    }

    public final void setPreserve_recipients(boolean preserve_recipients) {
        this.preserve_recipients = preserve_recipients;
    }

    public final String getSubaccount() {
        return subaccount;
    }

    public final void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    public boolean isAuto_html() {
        return auto_html;
    }

    public void setAuto_html(boolean auto_html) {
        this.auto_html = auto_html;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isInline_css() {
        return inline_css;
    }

    public void setInline_css(boolean inline_css) {
        this.inline_css = inline_css;
    }

    public boolean isMerge() {
        return merge;
    }

    public void setMerge(boolean merge) {
        this.merge = merge;
    }

    public String getMerge_language() {
        return merge_language;
    }

    public void setMerge_language(String merge_language) {
        this.merge_language = merge_language;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public Map<String, Object> getRecipient_metadata() {
        return recipient_metadata;
    }

    public void setRecipient_metadata(Map<String, Object> recipient_metadata) {
        this.recipient_metadata = recipient_metadata;
    }
}
