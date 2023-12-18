package com.moodvie.persistance.model;
import java.time.LocalDateTime;

public class Subscribe {
    private int id;
    private LocalDateTime startDate;
    private boolean isActive;

    public Subscribe() {}

    public Subscribe(int id, LocalDateTime startDate, boolean isActive) {
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
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
        * @param id the id to set
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * @return isActive
     */
    public boolean getIsActive() {
        return isActive;
    }

    /**
        * @param id the id to set
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

