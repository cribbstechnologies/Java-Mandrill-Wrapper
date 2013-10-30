package com.cribbstechnologies.clients.mandrill.model;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MandrillMessageAttachment {

    private String type;
	private String name;
	private String content;

    public MandrillMessageAttachment() {
    }

    public MandrillMessageAttachment(File file, String type) {
        this.name = file.getName();
        this.type = type;
        try {
            FileInputStream fin = new FileInputStream(file);
            byte data[] = new byte[(int)file.length()];
            fin.read(data);
            fin.close();
            this.content = new String(Base64.encodeBase64(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't encode attachment file " + file);
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
