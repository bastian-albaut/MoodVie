package com.moodvie.persistance.model;
import java.sql.Timestamp;

public class Subscribe {
    private int id;
    private Timestamp startDate;
    private boolean isActive;

    public Subscribe() {}

    public Subscribe(int id, Timestamp startDate, boolean isActive) {
        this.id = id;
        this.startDate = startDate;
        this.isActive = isActive;
    }
     
    // Getters et setters

    /**
        * @return the id
     */
    public int getId() {
        return id;
    }

    /**
        * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the startDate
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
        * @param startDate the startDate to set
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * @return isActive
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
        * @param isActive the isActive to set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", isActive='" + isActive + '\'' +
                '}';
    }
}

