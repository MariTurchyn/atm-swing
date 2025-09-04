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
### Option 1: Run in IntelliJ IDEA (Recommended)
1. Clone the repository:
   ```bash
   git clone https://github.com/MariTurchyn/atm-swing.git
2. Open the project in IntelliJ IDEA Community Edition (free).
3. IntelliJ will automatically detect Maven and download dependencies.
4. Run the Main class (click the green button).

### Option 2: Run from Command Line (requires Maven installed)

1. Clone the repository:
  git clone https://github.com/MariTurchyn/atm-swing.git
  cd atm-swing

2. Build the project:
  mvn clean package

3. Run the JAR:
  java -jar target/atm-swing-1.0-SNAPSHOT.jar
