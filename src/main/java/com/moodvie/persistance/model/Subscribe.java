package com.moodvie.persistance.model;
import java.sql.Timestamp;

public class Subscribe {
    private int id;
    private Timestamp startDate;
    private boolean isActive;
    private int typeOfSubscribeId;
    private int userId;

    public Subscribe() {}

    public Subscribe(Timestamp startDate, boolean isActive, int typeOfSubscribeId, int userId) {
        this.startDate = startDate;
        this.isActive = isActive;
        this.typeOfSubscribeId = typeOfSubscribeId;
        this.userId = userId;
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

    /**
     * @return the typeOfSubscribeId
     */
    public int getTypeOfSubscribeId() {
        return typeOfSubscribeId;
    }

    /**
     * @param typeOfSubscribeId the typeOfSubscribeId to set
     */
    public void setTypeOfSubscribeId(int typeOfSubscribeId) {
        this.typeOfSubscribeId = typeOfSubscribeId;
    }

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
    * @param userId the userId to set
    */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Subscribe{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", isActive='" + isActive + '\'' +
                ", typeOfSubscribeId='" + typeOfSubscribeId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}

