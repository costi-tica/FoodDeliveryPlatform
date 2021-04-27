# Food Delivery Platform

## Learning Java

---

### App logic:

Users can create an account or log in with an existing account.

After logging in, depending on the type of the account, users have different menu options (see app actions).

Users are automatically logged out after 2h of their current session.

Clients can place an order.

Orders are initially marked as 'PLACED'. If a courier decides to accept an order, that order is marked as 'ON_DELIVERY'. 
After the order has been completed, it is marked as 'COMPLETED'. 

### App actions:
* Users can register as
    + Clients
    + Couriers
    + Restaurant owners
* Clients have the following options:
    + Place an order
    + View all restaurants
    + Select a restaurant for further actions:
        * See the restaurant's info
        * See the restaurant's menu
        * Add a review
        * Edit their review
        * Delete their review
        * View other clients' reviews
    + View their past orders
    + View their active order status
    + Edit their account info:
        * Name
        * Phone number
        * Email
        * Password
        * Address
* Couriers have the following options:
    + Accept an order
    + Mark their active order as delivered
    + View orders available for delivery
    + Edit their account info:
        * Name
        * Phone number
        * Email
        * Password
* A restaurant owner has the following options:
    + Add his restaurant to the platform
    + Edit his restaurant:
        * Add a dish/drink category to menu
        * Add a dish/drink to menu in a specific category
        * Change the restaurant's name
        * Edit the restaurant's address
        * Edit a product
        * Delete a product
        * Delete a drink/dish category
        * See restaurant's reviews
    + Edit his account:
        * Name
        * Phone number
        * Email
        * Password
* Platform admins have the following options:
    + View all users
    + View all restaurants
    + Delete a user
    + Delete a restaurant