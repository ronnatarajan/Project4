package src;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    //username and password
    private String username;
    private String password;

    private boolean seller;

    private ArrayList<User> blocked;
    private ArrayList<User> blockedBy;


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
        this.blockedBy = new ArrayList<>();
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
    public void addBlocked(User user) {
        this.blocked.add(user);
        user.blockedBy.add(this);
    }

    /**
     * see if current user has blocked a passed in user
     * @param user User
     * @return true if current user has blocked another
     */
    public boolean hasBlocked(User user) {
        return this.blocked.contains(user);
    }

    /**
     * see if the current user is blocked by another
     * @param user potential person who blocked the current user
     * @return true if blocked by user, false if not
     */
    public boolean isBlockedBy(User user) {
        return this.blockedBy.contains(user);
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
        return seller == user.seller && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(blocked, user.blocked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, seller, blocked);
    }
}