package org.example.eventmanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Sid;
    long id;
    long usersid;
    boolean status;

    public Status() {
    }

    public long getSid() {
        return Sid;
    }

    public long getId() {
        return id;
    }

    public long getUsersid() {
        return usersid;
    }

    public boolean isStatus() {
        return status;
    }

    public void setSid(long sid) {
        Sid = sid;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsersid(long usersid) {
        this.usersid = usersid;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
