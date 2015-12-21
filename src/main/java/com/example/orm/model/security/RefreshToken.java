package com.example.orm.model.security;


import javax.persistence.*;

/**
 * Created by Georg on 4/1/2015.
 */
@Entity
@Table(name = "oauth_refresh_token")
public class RefreshToken {


    @Id
    @Column(name = "token_id",  nullable = false)
    private String id;

    @Column(name = "token",  nullable = false)
    private byte[] token;


    @Column(name = "authentication", nullable = false)
    private byte[]  authentication;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}
