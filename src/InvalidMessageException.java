package src;

/**
 * Project 4 -- Messaging System
 *
 *  CLASS DESCRIPTION
 *
 * @author NAME, lab sec 23
 *
 * @version November 13, 2023
 */

public class InvalidMessageException extends Exception {
    public InvalidMessageException(String message) {
        super(message);
    }
}
