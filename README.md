# CS2212---Software-Engineering
This repository contains skeleton code of a Warehouse Management System provided by the course. I and 2 other members in my team utilized design patterns to make the code comply with standards provided by the course.

## What is the Warehouse Management System about?

The Warehouse Management System (WMS) is a software system that simulates the operations of the warehouse of a simple company that sells goods to clients, and buys products from suppliers. The model of the warehouse of the company is implemented as a database with one table denoting the ID of each product, the name of the product, its stock quantity, and its unit price. Once a specific quantity of product is sold to a customer, this quantity is taken out of the available stock of this product from the corresponding database entry pertaining to this product. If the stock quantity of a product drops below 10 items or an order cannot be fulfilled the product’s state is set as “out of stock” (see packages ca.uwo.model  and ca.uwo.model.item.states for the different states a product can be in). Once a product’s state is “out of stock” then a notification is sent so that a purchase (i.e. restock) action can be triggered and the item be restocked from a supplier. Each product is restocked according to a specific strategy (e.g. a restocking strategy for a product can be 50 units per restock operation, while for another product a different restocking strategy could be used, leading thus to a different restock quantities to be added). In addition to the restocking strategies, the price of an individual product for a given order can be calculated using different strategies (e.g. orders of apples more than 100 units get a 10% discount for this product), and the total price for the whole order (an order may contain many products with different quantities each) is also calculated using different strategies (e.g. orders of total value more than 1,000 dollars get an additional aggregate 5% discount to any other individual discounts applied for each product in this order).

## Important
You must add sqlite jdbc as an external jar to the project

## How to Install the Program
How to install the program here
Search up archiving maven project and rebuidling it

"Please be sure to include a README file outlining how to build and run your submission for acceptance testing."

"Please submit your code as an archive file (full Maven Eclipse Project - not just the java source files) to OWL by April 7, 23:55."

## How to Run the Program
Run the Driver class (its main) as a java application from Eclipse.
The system will execute the orders specified in the driver_file 
Once the system starts printing constantly in the console the message 
test


