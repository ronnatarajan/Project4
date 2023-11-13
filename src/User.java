package src;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class User {
    //username and password
    private String username;
    private String password;

    private boolean seller;

    private ArrayList<User> blocked;

    private ArrayList<User> invisible;

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
        this.blocked = new ArrayList<>();
        this.invisible = new ArrayList<>();
    }



    public int sizeofblocked() {
        return this.blocked.size();
    }

    public User(String username, boolean seller) {
        this.username = username;
        this.seller = seller;
        this.blocked = new ArrayList<>();
        this.invisible = new ArrayList<>();

    }


    /**
     * getter for username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    public void setBlocked(ArrayList<User> blocked) {
        this.blocked = blocked;
    }

    public void setInvisible(ArrayList<User> invisible) {
        this.invisible = invisible;
    }

    public ArrayList<User> getBlocked() {
        return blocked;
    }

    public ArrayList<User> getInvisible() {
        return invisible;
    }

    /**
     * getter for password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    public boolean canSee(User user) {
        for (User u : invisible) {
            if (u.getUsername().equals(user.getUsername())) {
                return false;
            }
        }
        return true;
    }

    public void makeInvisible(User user) {
        user.invisible.add(this);
    }
    /**
     * validate password
     * @param password String
     * @return true if correct password was entered
     */
    public boolean correctPassword(String password) {return this.password.equals(password);}
    /**
     * getter for seller
     *
     * @return boolean
     */
    public boolean isSeller() {
        return seller;
    }

    /**
     * add a user to the list of people the current user has blocked
     * @param user User
     */
    public void block(User user) {
        this.blocked.add(user);
    }

    /**
     * see if current user has blocked a passed in user
     * @param user User
     * @return true if current user has blocked another
     */
    public boolean hasBlocked(User user) {
        for (User u : blocked) {
            if (u.getUsername().equals(user.getUsername())) {
                return true;
            }
        }
        return false;
    }


    /**
     * sees if this object is the same as another
     * @param o other User object
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, seller, blocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
