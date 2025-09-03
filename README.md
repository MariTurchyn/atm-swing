# ATM Simulation (Java Swing)

A simple desktop **ATM system** built with **Java Swing** and **Maven**.  
It demonstrates object-oriented design, GUI development, and basic financial operations like login, deposits, withdrawals, and balance checks.

## Features
- Login with card number & PIN  
- Check account balance  
- Deposit and withdraw money (with error handling)  
- Logout for secure session  
- Modern UI with FlatLaf  

##  Tech Stack
- Java 17/18  
- Swing (GUI)  
- Maven (build & dependency management)  
- FlatLaf (modern look for Swing)  
- *(Planned)* SQLite/MySQL for persistent data  

##  How to Run
```bash
git clone https://github.com/<your-username>/atm-swing.git
cd atm-swing
mvn clean package
java -jar target/atm-swing-1.0.jar
