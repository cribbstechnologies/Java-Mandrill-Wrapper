package com.cribbstechnologies.clients.mandrill.model;

import java.util.List;
import java.util.Map;

public class MandrillMessage {

<<<<<<< HEAD
	private String text;
	private String subject;
	private String from_email;
	private String from_name;
	private MandrillRecipient[] to;
	private String bcc_address;
	private boolean track_opens=false;
	private boolean track_clicks=false;
	private boolean auto_text=false;
	private boolean url_strip_qs=false;
	private String[] tags = new String[0];
	private String[] google_analytics_domains = new String[0];
	private String[] google_analytics_campaign = new String[0];
    private List<MergeVar> global_merge_vars;
	
	private Map<String, String> headers;
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getFrom_email() {
		return from_email;
	}
	
	public void setFrom_email(String from_email) {
		this.from_email = from_email;
	}
	
	public String getFrom_name() {
		return from_name;
	}
	
	public void setFrom_name(String from_name) {
		this.from_name = from_name;
	}
	
	public MandrillRecipient[] getTo() {
		return to;
	}
	
	public void setTo(MandrillRecipient[] to) {
		this.to = to;
	}
	
	public String getBcc_address() {
		return bcc_address;
	}
	
	public void setBcc_address(String bcc) {
		this.bcc_address = bcc;
	}

	public boolean isTrack_opens() {
		return track_opens;
	}
	
	public void setTrack_opens(boolean track_opens) {
		this.track_opens = track_opens;
	}
	
	public boolean isTrack_clicks() {
		return track_clicks;
	}
	
	public void setTrack_clicks(boolean track_clicks) {
		this.track_clicks = track_clicks;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(Map<String, String> struct) {
		this.headers = struct;
	}

	public boolean isAuto_text() {
		return auto_text;
	}

	public void setAuto_text(boolean auto_text) {
		this.auto_text = auto_text;
	}

	public boolean isUrl_strip_qs() {
		return url_strip_qs;
	}

	public void setUrl_strip_qs(boolean url_strip_qs) {
		this.url_strip_qs = url_strip_qs;
	}

	public String[] getGoogle_analytics_domains() {
		return google_analytics_domains;
	}

	public void setGoogle_analytics_domains(String[] google_analytics_domains) {
		this.google_analytics_domains = google_analytics_domains;
	}

	public String[] getGoogle_analytics_campaign() {
		return google_analytics_campaign;
	}

	public void setGoogle_analytics_campaign(String[] google_analytics_campaign) {
		this.google_analytics_campaign = google_analytics_campaign;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
=======
    private String text;
    private String subject;
    private String from_email;
    private String from_name;
    private String subaccount;
    private MandrillRecipient[] to;
    private boolean track_opens = false;
    private boolean track_clicks = false;
    private boolean auto_text = false;
    private boolean url_strip_qs = false;
    private boolean preserve_recipients = false;
    private String[] tags = new String[0];
    private String[] google_analytics_domains = new String[0];
    private String[] google_analytics_campaign = new String[0];
    private List<MergeVar> global_merge_vars;
    List<MessageMergeVars> merge_vars;
    List<MandrillAttachment> attachments;
>>>>>>> 4da8cd42c2f2c9a7191d65645100747c130c98a3

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

}
