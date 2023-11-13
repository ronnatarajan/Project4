package src;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author Ron Natarajan, lab sec 23
 *
 * @version November 13, 2023
 */

public class Message {
    //saves message
    String message;
    //sender
    User sender;
    //recipient
    User recipient;

    /**
     * initialize message
     * sellers cannot message sellers
     * buyers cannot message buyers
     * a sender cannot message a recipient if the recipient has blocked them
     * @param message String
     * @param sender User
     * @param recipient User
     */
    public Message(String message, User sender, User recipient) throws InvalidMessageException {
        //if a Seller is trying to message a seller, throw InvalidMessageException
        //If a buyer is trying to message another buyer, throw InvalidMessageException
        if (sender.isSeller() == recipient.isSeller()) {
            if (sender.isSeller()) {
                throw new InvalidMessageException("Seller cannot message another seller");
            } else {
                throw new InvalidMessageException("Buyer cannot message another buyer");
            }
        }

        //check to make sure recipient has not blocked sender, else throw InvalidMessageException
        if (recipient.hasBlocked(sender)) {
            throw new InvalidMessageException("You have been blocked");
        }
        this.message = message;
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * getter for message
     * @return String
     */
    public String getMessage() {
        return message;
    }

    /**
     * getter for sender
     * @return User
     */
    public User getSender() {
        return sender;
    }

    /**
     * getter for recipient
     * @return User
     */
    public User getRecipient() {
        return recipient;
    }

    /**
     * toString for a message
     * @return username, message, and recipient
     */
    @Override
    public String toString() {
        return "Sender: " + sender.getUsername() + "; Message: " + this.message + "; Recipient: " + recipient.getUsername();
    }
}
