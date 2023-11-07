package src;

public class Message {
    //saves message
    String message;
    //sender
    User sender;
    //recipient
    User recipient;

    /**
     * initialize message
     * @param message String
     * @param sender User
     * @param recipient User
     */
    public Message(String message, User sender, User recipient) throws InvalidMessageException {
        if (sender.isSeller() == sender.isSeller()) {
            if (sender.isSeller()) {
                throw new InvalidMessageException("Seller cannot message another seller");
            } else {
                throw new InvalidMessageException("Buyer cannot message another buyer");
            }
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
     * @return String
     */
    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }
}
