package com.cribbstechnologies.clients.mandrill.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

public class MandrillAttachment {

    String type;
    String name;
    String content;

    public MandrillAttachment(String type, String name, String content) {
        this.type = type;
        this.name = name;
        this.content = content;
    }

    public MandrillAttachment(File file, String type) {
        this.name = file.getName();
        this.type = type;
        try {
            FileInputStream is = new FileInputStream(file);
            this.content = new String(Base64.encodeBase64(IOUtils.toByteArray(is)));
            is.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't encode attachment file " + file);
        }
    }

    public MandrillAttachment(InputStream is, String fileName, String type) {
        this.name = fileName;
        this.type = type;
        try {
            this.content = new String(Base64.encodeBase64(IOUtils.toByteArray(is)));
        } catch (IOException e) {
            throw new RuntimeException("Can't encode attachment " + fileName);
        }
    }

    public String getContent() {
        return this.content;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    /**
     * The attachment is represented by a base-64-encoded string
     * 
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

}
