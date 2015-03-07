package com.nwchecker.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <h1>Role Entity</h1>
 * Entity that represents one request of some User in DB.
 * <p>
 *
 * @author Serhii Dovhaniuk
 * @version 1.0
 * @since 2015-01-23
 */
@Entity
@Table(name = "UserRequest")
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestId")
    private int requestId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @Column(name = "request")
    private String request;

    public UserRequest() {
    }

    public UserRequest(User user, String request) {
        this.user = user;
        this.request = request;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRequest that = (UserRequest) o;

        if (request != null ? !request.equals(that.request) : that.request != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }



    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        return result;
    }
}