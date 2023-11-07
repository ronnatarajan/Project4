package src;

import java.util.ArrayList;

public class User {
    //username and password
    private String username;
    private String password;

    private boolean seller;


    /**
     * initialize User object
     *
     * @param username String
     * @param password String
     */
    public User(String username, String password, boolean seller) {
        this.username = username;
        this.password = password;
        this.seller = seller;
    }

    /**
     * getter for username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * getter for password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * getter for seller
     *
     * @return boolean
     */
    public boolean isSeller() {
        return seller;
    }
}