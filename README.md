# Smart Sports Management System

A robust and aesthetically pleasing Desktop Application developed in **Java (Swing/AWT)** and backed by a **MySQL** database. This project serves as a comprehensive system for managing sports tournaments, player records, and points tables while raising awareness for Sustainable Development Goals (SDGs 3 & 4).

## 🚀 Features

- **Player Management** 🏃‍♂️
  - Register new players with details (ID, Name, Sport, Age, Department).
  - View a complete list of all registered players.
  - Search for players by their unique ID.
  - Remove players dynamically from the database.
- **Tournament Management** 🏆
  - Register new tournaments with Name, Date, and Venue.
  - View the upcoming Tournament Schedule.
- **Points Table** 📈
  - Keep track of team standings.
  - Record matches played, won, lost, and total points.
- **SDG Goals Integration** 🌍
  - A dedicated module highlighting how sports align with **SDG 3 (Good Health and Well-being)** and **SDG 4 (Quality Education)**.

## 💻 Tech Stack

- **Frontend:** Java Swing, AWT (Custom UI Utils for centralized styling)
- **Backend:** Core Java (JDK 21)
- **Database:** MySQL (JDBC Connector)

## 🎨 UI/UX Highlights

- Built using a strictly DRY (Don't Repeat Yourself) principle via a shared `UIUtils` class.
- "Excellent Category" graphical aesthetics featuring:
  - Custom Color Palettes (`#2C3E50`, `#3498DB`, `#E74C3C`, `#27AE60`)
  - Smooth Segoe UI Typography
  - Non-focusable modern buttons with dynamic hover/action listeners
  - Clean JTable and Form alignments using `GridBagLayout` and `BorderLayout`.

## 🛠️ How to Run

1. **Database Setup**
   - Create a MySQL database named `sportsdb`.
   - Update `DBConnection.java` with your MySQL `root` username and password if different from the default.
   - Tables required: `players`, `tournament`, `points_table`.
2. **Compilation & Execution**
   - Ensure you have JDK installed.
   - Navigate to the project directory in your terminal.
   - Compile the files:
     ```bash
     javac *.java
     ```
   - Run the Main application:
     ```bash
     java Main
     ```
3. **Default Login**
   - **Username:** Praneel
   - **Password:** Praneel@123

## 🤝 Contribution

This project is actively maintained. To contribute, fork the repository, make your changes in a separate branch, and raise a pull request.
