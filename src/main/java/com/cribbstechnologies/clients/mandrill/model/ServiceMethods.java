package com.cribbstechnologies.clients.mandrill.model;

public class ServiceMethods {

    public class Users {

        public static final String PING = "users/ping.json";
        public static final String INFO = "users/info.json";
        public static final String SENDERS = "users/senders.json";
        public static final String PING2 = "users/ping2.json";
        @Deprecated
        public static final String DISABLE_SENDER = "users/disable-sender.json";
        @Deprecated
        public static final String VERIFY_SENDER = "users/verify-sender.json";
    }

    public class Messages {

        public static final String SEND = "messages/send.json";
        public static final String SEND_TEMPLATE = "messages/send-template.json";
        public static final String SEARCH = "messages/search.json";
        public static final String PARSE = "messages/parse.json";
        public static final String SEND_RAW = "messages/send-raw.json";
    }

    public class Tags {

        public static final String LIST = "tags/list.json";
        public static final String DELETE = "tags/delete.json";
        public static final String INFO = "tags/info.json";
        public static final String TIME_SERIES = "tags/time-series.json";
        public static final String ALL_TIME_SERIES = "tags/all-time-series.json";
    }

    public class Rejects {

        public static final String ADD = "rejects/add.json";
        public static final String LIST = "rejects/list.json";
        public static final String DELETE = "rejects/delete.json";
    }

    public class Whitelists {

        public static final String ADD = "whitelists/add.json";
        public static final String LIST = "whitelists/list.json";
        public static final String DELETE = "whitelists/delete.json";
    }

    public class Senders {

        public static final String LIST = "senders/list.json";
        public static final String DOMAINS = "senders/domain.json";
        public static final String INFO = "senders/info.json";
        public static final String TIME_SERIES = "senders/time-series.json";
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
        public static final String PUBLISH = "templates/publish.json";
        public static final String DELETE = "templates/delete.json";
        public static final String LIST = "templates/list.json";
        public static final String TIME_SERIES = "templates/time-series.json";
        public static final String RENDER = "templates/render.json";
    }

    public class Webhooks {

        public static final String LIST = "webhooks/list.json";
        public static final String ADD = "webhooks/add.json";
        public static final String INFO = "webhooks/info.json";
        public static final String UPDATE = "webhooks/update.json";
        public static final String DELETE = "webhooks/delete.json";
    }

    public class Inbound {

        public static final String DOMAINS = "inbound/domains.json";
        public static final String ROUTES = "inbound/routes.json";
        public static final String SEND_RAW = "inbound/send-raw.json";
    }

    public class Exports {

        public static final String INFO = "exports/info.json";
        public static final String LIST = "exports/list.json";
        public static final String REJECTS = "exports/rejects.json";
        public static final String WHITELIST = "exports/whitelist.json";
        public static final String ACTIVITY = "exports/activity.json";
    }
}
