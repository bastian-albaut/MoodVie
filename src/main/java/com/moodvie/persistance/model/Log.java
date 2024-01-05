package com.moodvie.persistance.model;

import java.util.Date;
public class Log {
    Date date;
    String action;
    String idUser;

    int idLog;

    String nameUser;

    public Log(String action, String idUser, String nameUser) {
        this.date = new Date();
        this.action = action;
        this.idUser = idUser;
        this.idLog = idLog;
        this.nameUser = nameUser;
    }

    public Log() {}

    public Date getDate() {
        return date;
    }

    public String getAction() {
        return action;
    }

    public String getIdUser() {
        return idUser;
    }

    public int getIdLog() {
        return idLog;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

}
