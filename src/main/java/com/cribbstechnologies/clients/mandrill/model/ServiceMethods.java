package com.cribbstechnologies.clients.mandrill.model;

public class ServiceMethods {
	
	public class Users {
		public static final String PING = "users/ping.json";
		public static final String INFO = "users/info.json";
		public static final String SENDERS = "users/senders.json";
		public static final String DISABLE_SENDER = "users/disable-sender.json";
		public static final String VERIFY_SENDER = "users/verify-sender.json";
	}
	
	public class Messages {
		public static final String SEND = "messages/send.json";
		public static final String SEND_TEMPLATE = "messages/send-template.json";
	}
	
	public class Tags {
		public static final String LIST = "tags/list.json";
		public static final String TIME_SERIES = "tags/time-series.json";
		public static final String ALL_TIME_SERIES = "tags/all-time-series.json";
	}
	
	public class Urls {
		public static final String LIST = "urls/list.json";
		public static final String SEARCH = "urls/search.json";
		public static final String TIME_SERIES = "urls/time-series.json";
	}

	public class Templates {
		public static final String ADD = "templates/add.json";
		public static final String INFO = "templates/info.json";
		public static final String UPDATE = "templates/update.json";
		public static final String DELETE = "templates/delete.json";
		public static final String LIST = "templates/list.json";
	}
}
