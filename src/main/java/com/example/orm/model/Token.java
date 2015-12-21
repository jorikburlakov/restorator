package com.example.orm.model;


import javax.persistence.*;

/**
 * Created by Georg on 4/1/2015.
 */
@Entity
@Table(name = "oauth_access_token")
public class Token {


    @Id
    @Column(name = "token_id", nullable = false)
    private String id;

    @Column(name = "token", nullable = false)
    private byte[] token;

    @Column(name = "authentication_id", nullable = false)
    private String authentication_id;

    @Column(name = "user_name", nullable = false)
    private String user_name;


    @Column(name = "client_id", nullable = false)
    private String client_id;

    @Column(name = "authentication", nullable = false)
    private byte[] authentication;

    @Column(name = "refresh_token", nullable = false)
    private String refresh_token;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAuthentication_id() {
        return authentication_id;
    }

    public void setAuthentication_id(String authentication_id) {
        this.authentication_id = authentication_id;
    }


    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
