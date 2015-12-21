package com.example.orm.model.security;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Georg on 4/1/2015.
 */
@Entity
@Table(name = "oauth_code")
public class OAuthCode {

    @Id
    @Column(name = "code", nullable = false)
    private String code;


    @Column(name = "authentication", unique = true, nullable = false)
    private byte[] authentication;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}
