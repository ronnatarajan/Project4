# # Project4
### Compiling
To compile the project, simply run MainMenu.java and follow instructions prompted.
<br/>** import src is in our GitHub workspace because IntelliJ would not let us use directories, only packages. In Vocareum, we removed import src.
### Submissions
Ryan - Submitted report on Brightspace  
Josh - Submitted Vocareum workspace
### Class Descriptions
Accounts:
<br/>- addCustomerAccount is a static method that writes a created customer account to CustomerAccountsList.txt.
<br/>- addSellerAccount is a static method that writes a created seller account to SellerAccountsList.txt.
<br/>- checkAccount is a static method that verifies whether or not an account trying to login is in the list files
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

EditDelete:
<br/>- deleteMessage is a static method that deletes a specified message from the user's file
<br/>- editMessage is a static method that allows a user to edit their message and that new message will be rewritten to their respective user file
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class


FileInExp:
<br/>- exportMessage is a static method that allows a user's messages to exported as a csv file
<br/>- importMessage is a static method that allows a csv file to be imported into a user's file as a message
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

InvalidMessageException:
<br/> throws a custom exception message for various exceptions in Message class
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

MainMenu:
<br/>- MainMenu naviagates through our entire program, combining all the methods from each class based on the scenarioes selected
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

Message:
<br/>- creates objects and for Messages with the fields message, sender, and recipient
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

Parse:
<br/>- getUsers reads the CustomerList.txt file and places all those users in a list
<br/>- businesses reads the StoresList.txt file and places all the seller emails and businesses in a hash map
<br/>- getMessages reads the file of an individual user and places all their messages into a list
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

SendMessages:
<br/>- sellerSendsMesssage sends a message to a customer from a seller by appending the message to bother their respective text file
<br/>- customerSendsMessage sends a message to a seller from a customer by appending the message to both their respective text file
<br/>- appendMessage handles appending the messages to sender and receiver's text files in the specified format
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

Stores:
<br/>- appendStores writes a seller's created store and their email to the StoresList.txt file
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class

User:
<br/>- creates a User object with various fields determining their username, password, whether or not they are a seller, who they have blocked, and who they have set invisible
<br/> *all methods called within MainMenu
<br/> *Tested with respective Test Case class
