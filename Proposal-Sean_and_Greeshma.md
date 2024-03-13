**COMP730 + 830 Final Software Project Proposal**

**Team Members:**

`	`Sean Burwen, Greeshma Srinidhi

**Project Goal:** 

`	`Storefront Application (e-commerce)

**Major Features + Functions:**

- User Authentication
  - Registration and Login for accounts with personal details.
  - Persistent Accounts with editable profiles.
- Product Listings
  - Each listing is an object which contains a title, description, image, price, and quantity.
  - Users can create a listing of their own to sell a product.
  - All listings can be viewed in a “listings browser” which would be scrollable with filter and search functions.
- Shopping Cart
  - Products can be added to a user’s cart, which should reduce the remaining quantity of that product.
  - Users should not be able buy products that they’ve posted.
  - The Cart window will display the contents of the user’s cart, calculate a total price for them, and allow the user to remove items from it.
- Checkout
  - The user should be able to complete a purchase for the products in their cart by providing “payment details” and a shipping address.
  - The checkout process should produce a receipt which can be printed and emailed to the user.
- Admin Panel
  - Provides a way for a “root” user to login and have permissions to edit any of the listings. They would be able to create, delete, and edit existing listings.

**Achievement Level – 50%:**

- Working template for creating individual listings.
- Basic listings browser which displays all current listings (to be improved at 100%).
- Basic cart functionality, assuming the program is only one user.

**Achievement Level – 75%:**

- Working user authentication with all account details.
- Per-user carts only (in 50% level, we only had one global cart).
- Complete checkout functionality with receipt generator.

**Achievement Level – 100%:**

- Improve the listings browser by adding filter and search functionalities.
- Create the admin panel to allow a moderator to edit listings.
- Allow users to edit their account information (change of phone number or email, etc.).

**Full Description:**

The general idea of this project is to create an e-commerce platform where users can browse a list of products which can be added to their cart and then purchased. This list will have improved functionalities which include search and filtering. Users will be able to create their own product listings which other users can then add to their carts. A user can also go back and edit a listing that they’ve created in the past. Each user will have a full account which contains their personal information including name, email, phone number, address, and payment details. Once a user has filled their cart, they can checkout to complete the purchase. This will provide them with a receipt which can be printed and emailed to the user. In addition to the standard user accounts, there will also be an “administrator” account which has access to edit any listings posted by any of the users.

