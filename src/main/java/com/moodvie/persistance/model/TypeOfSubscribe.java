package com.moodvie.persistance.model;

public class TypeOfSubscribe {
    private int id;
    private String label;
    private double price;
    private int numberOfDays;

    public TypeOfSubscribe() {}

    public TypeOfSubscribe(String label, double price, int numberOfDays) {
        this.label = label;
        this.price = price;
        this.numberOfDays = numberOfDays;
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

     /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.price = price;
    }

    /**
     * @return the numberOfDays
     */
    public int getNumberOfDays() {
        return numberOfDays;
    }

    /**
     * @param numberOfDays the numberOfDays to set
     */
    public void setNumberOfDays(int numberOfDays) {
        if (numberOfDays < 0) {
            throw new IllegalArgumentException("Number of days must be positive");
        }
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "TypeOfSubscribe{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", price='" + price + '\'' +
                ", numberOfDays='" + numberOfDays + '\'' +
                '}';
    }
}
