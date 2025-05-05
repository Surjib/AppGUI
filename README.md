# Project Overview
- Name: AppGUI
- Description: A JavaFX application designed for monitoring and analyzing Sampled Values (IEC61850).
- Technologies Used: Java, JavaFX, Lombok, Pcap4j.
# Dependencies:
- JavaFX Controls (version 17.0.2)
- JUnit (version 5.8.2)
- Pcap4j (2.0.0-alpha.6)
- WinPcap (4.1.3)
# Structure
- src/main/java/com/example/appgui: Contains the main application logic.
- Application.java: The entry point of the JavaFX application.
- TableContent.java: Represents the data structure for table entries.
- EthernetListener.java: Handles Ethernet packet listening.
- src/main/resources/com/example/appgui: Contains FXML and resource files for the UI.
# Getting Started
- Clone the repository: git clone <repository-url>
- Build the project: Use Maven to build the project.
- Run the application: Execute the main class Application.
# Running Tests
- Tests can be run using JUnit.

![alt text](https://github.com/Surjib/AppGUI/blob/main/src/main/resources/com/example/appgui/Screenshot%202025-05-05%20105701.png)

![alt text](https://github.com/Surjib/AppGUI/blob/main/src/main/resources/com/example/appgui/Screenshot%202025-05-05%20105757.png)
