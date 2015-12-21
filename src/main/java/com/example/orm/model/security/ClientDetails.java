package com.example.orm.model.security;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Georg on 4/1/2015.
 */
@Entity
@Table(name = "oauth_client_details")
public class ClientDetails {

    @Id
    @Column(name = "client_id", nullable = false)
    private String id;

    @Column(name = "resource_ids", nullable = false)
    private String resource_ids;

    @Column(name = "client_secret", nullable = false)
    private String client_secret;

    @Column(name = "scope", nullable = false)
    private String scope;

    @Column(name = "authorized_grant_types", nullable = false)
    private String authorized_grant_types;

    @Column(name = "web_server_redirect_uri")
    private String web_server_redirect_uri;

    @Column(name = "authorities ", nullable = false)
    private String authorities ;

    @Column(name = "autoapprove ", nullable = false)
    private String autoapprove ;

    @Column(name = "access_token_validity")
    private Integer access_token_validity;

    @Column(name = "refresh_token_validity")
    private Integer refresh_token_validity;

    @Column(name = "additional_information", nullable = false)
    private String additional_information;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResource_ids() {
        return resource_ids;
    }

    public void setResource_ids(String resource_ids) {
        this.resource_ids = resource_ids;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getAuthorized_grant_types() {
        return authorized_grant_types;
    }

    public void setAuthorized_grant_types(String authorized_grant_types) {
        this.authorized_grant_types = authorized_grant_types;
    }

    public Integer getAccess_token_validity() {
        return access_token_validity;
    }

    public void setAccess_token_validity(Integer access_token_validity) {
        this.access_token_validity = access_token_validity;
    }

    public Integer getRefresh_token_validity() {
        return refresh_token_validity;
    }

    public void setRefresh_token_validity(Integer refresh_token_validity) {
        this.refresh_token_validity = refresh_token_validity;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }

    public String getWeb_server_redirect_uri() {
        return web_server_redirect_uri;
    }

    public void setWeb_server_redirect_uri(String web_server_redirect_uri) {
        this.web_server_redirect_uri = web_server_redirect_uri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAutoapprove() {
        return autoapprove;
    }

    public void setAutoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
    }


}
