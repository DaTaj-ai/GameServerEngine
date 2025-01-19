# GameServerEngine
![Server - Made with Clipchamp (1)](https://github.com/user-attachments/assets/5eb372ac-7e2b-4228-a14f-52d2471e04a7)

## Overview

The **GameServerEngine** is a server engine designed to connect multiple players and manage seamless multiplayer gaming. It provides robust features for handling player interactions, game requests, and maintaining efficient gameplay connections.

[The Client for this Server]

---

## Features

- **Multiplayer Support:** Enable seamless interaction between multiple players.
- **Real-time Statistics:** Real-time graphs for online, available, and offline user statistics.
- **Server Control:** Start and stop the server effortlessly.

---

## Technology Used

- **Database:** SQLite for efficient data storage ([sqlite-jdbc-3.46.0.0.jar](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.46.0.0)).
- **Frontend:** JavaFX for building user interfaces.
- **Data Handling:** JSON for managing requests and responses.
- **Networking:** Networking capabilities for real-time communication.

---

## Installation

Follow these steps to set up the **GameServerEngine** locally:

### Prerequisites

- Java Development Kit (JDK) 8
  - Libraries:
    - [sqlite-jdbc-3.46.0.0.jar](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.46.0.0)
    - [slf4j-simple-2.0.7.jar](https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/2.0.7/)
    - [slf4j-api-2.0.7.jar](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/2.0.7)
    - [gson-2.10.1.jar](https://mvnrepository.com/artifact/com.google.code.gson/gson/2.10.1)

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/DaTaj-ai/GameServerEngine.git
   ```
2. Navigate to the project directory:
   ```bash
   cd GameServerEngine/dist
   ```
3. Run the server:
   ```bash
   java -jar target/GameServerEngine.jar
   ```

---

## Usage

1. Start the server as described in the installation steps.
2. Click on the Start Button.
3. The client connects to the server automatically.

---

## Contributors

- Mohamed Taj Eldin
- Abdelrahman Kamel
- Nourhan Essam
- Jailan Medhat
- Abram Morris

---

