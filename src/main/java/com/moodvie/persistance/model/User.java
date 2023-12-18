package com.moodvie.persistance.model;

public class User {
    private int id;
    private String pseudo;

    private String firstname;

    private String lastname;

    private String birthday;
    private String email;
    private String password;
    // autres propriétés selon vos besoins

    public User() {}


    public User(String pseudo, String firstname, String lastname, String birthday, String email, String password) {
        this.pseudo = pseudo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.email = email;
        this.password = password;
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
        * @return the pseudo
      */
    public String getPseudo() {
        return pseudo;
    }

    /**
        * @param pseudo the pseudo to set
     * */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
        * @return the firstname
     * */
    public String getFirstname() {
        return firstname;
    }

    /**
        * @param firstname the firstname to set
     * */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
        * @return the lastname
     * */
    public String getLastname() {
        return lastname;
    }

    /**
        * @param lastname the lastname to set
     * */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
        * @return the birthday
     * */
    public String getBirthday() {
        return birthday;
    }

    /**
        * @param birthday the birthday to set
     * */

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
        * @return the email
     * */

    public String getEmail() {
        return email;
    }

    /**
        * @param email the email to set
     * */

    public void setEmail(String email) {
        this.email = email;
    }

    /**
        * @return the password
     * */
    public String getPassword() {
        return password;
    }

    /**
        * @param password the password to set
     * */
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

