# Sales Taxes Application

This is a Spring Boot application that calculates sales taxes and generates receipts based on specified rules.


Example Input and Output

## Input:


1 book at 12.49
1 music CD at 14.99
1 chocolate bar at 0.85

## Output:


1 book: 12.49
1 music CD: 16.49
1 chocolate bar: 0.85
Sales Taxes: 1.50
Total: 29.83


### **Technologies Used**

Java 17
Spring Boot 3.1.0
JUnit 5 for unit testing
Maven for dependency management

## How to Run the Application

Prerequisites
Java 17 or higher installed.
Maven installed.

### Steps

Clone the repository:


git clone https://github.com/ahmedEzzine9/sales-taxes.git
cd sales-taxes

### Build the application:


mvn clean install

### Run the application:

mvn spring-boot:run

Then enter the path of a txt file that match following format:

n name at price
1 music CD at 14.99
1 chocolate bar at 0.85
