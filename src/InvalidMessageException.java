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

public class InvalidMessageException extends Exception {
    /**
     * constructor for InvalidMessageException
     * @param message String
     */
    public InvalidMessageException(String message) {
        super(message);
    }
}
