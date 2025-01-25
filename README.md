# Teacher and Class Sessions Management Project  

## Project Description  

This JavaFX application allows for the management of school schedules. It includes the following features:  
- Teacher registration.  
- Class session registration.  
- Display of schedules by class and subject.  

The project uses **MySQL** for data management and **JavaFX** for the graphical user interface.  

---

### Key Features:  
- A **Login/Register section** to secure access to the application and differentiate between users.  
- **Teacher registration form**: Allows adding, modifying, deleting, and searching for teachers in the database.  
- **Class session registration form**: Allows adding class sessions, selecting a class, subject, day, time, and the associated teacher.  
- **Display of teachers and sessions**: Tables are used to show the list of registered teachers and sessions in the database.  
- **Queries**: Enables users to consult and filter sessions based on various criteria.  

---

## Technologies Used  

- **JavaFX**: For creating graphical interfaces and managing the display.  
- **FXML**: For declaratively defining user interfaces.  
- **MySQL**: For database management, storing teacher and session information.  
- **CSS**: For styling and customizing graphical elements.  

---

## Development Steps  

To ensure the project functions correctly, certain libraries and dependencies must be added:  

1. **JDBC (Java Database Connectivity)**: To interact with the MySQL database from Java.  
   - Download the MySQL JDBC connector and add the `.jar` file to your project.  

2. **JavaFX**:  
   - If you are using Java 11 or later, you must separately add the JavaFX libraries, as they are no longer included in the default JDK.  
   - Download JavaFX from Gluon's official website and configure your project to include the necessary `.jar` files.  

3. **Add Libraries in VS Code**:  
   - Place the required `.jar` files in your project's `lib` folder.  
   - Modify your project settings to include the paths to these libraries in the classpath.  

---

### 1. **Database Design**  
- Create the `emploidutemps_db` database in MySQL.  
- Create the `enseignants`, `seances`, and `admin` tables with the following columns:  

   ```sql
   CREATE DATABASE emploidutemps_db;
   
   CREATE TABLE enseignants (
       id INT AUTO_INCREMENT PRIMARY KEY,
       nom VARCHAR(255),
       matricule VARCHAR(50) UNIQUE,
       contact VARCHAR(50)
   );
   
   CREATE TABLE seances (
       id INT AUTO_INCREMENT PRIMARY KEY,
       id_enseignant INT,
       classe VARCHAR(50),
       matiere VARCHAR(100),
       jour VARCHAR(50),
       heure VARCHAR(50),
       FOREIGN KEY (id_enseignant) REFERENCES enseignants(id)
   );
   
   CREATE TABLE admin (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       email VARCHAR(255) NOT NULL UNIQUE,
       password VARCHAR(255) NOT NULL
   );
   


### 2. **Graphical Interface Development Using Scene Builder**  

Scene Builder is a graphical tool for designing JavaFX interfaces without directly writing XML code (FXML).  

**Steps to use Scene Builder in your project:**  
1. Download and install Scene Builder from [here](https://gluonhq.com/products/scene-builder/).  
2. Create FXML files for each application view (e.g., `enseignantView.fxml` for the teacher interface).  
3. In Scene Builder, drag and drop graphical elements (buttons, text fields, tables) to visually create the interface.  
4. Associate each graphical element with a method in your Java code.  
5. Once the interface is created, you will obtain an FXML file that can be loaded in your Java code for display.  

---

### 3. **Java Controller Development**  

Develop the controller classes to handle the logic for user interactions with the graphical elements.  
Each controller should be linked to its corresponding FXML file to handle events and manage data.  

---

### 4. **Interface Customization with CSS**  

Style the graphical elements to enhance the user experience and improve the visual appearance.  
- Use a `style.css` file to define the appearance of buttons, text fields, and other elements.  
- Example CSS styling:  
    ```css
    .button {
        -fx-background-color: #4CAF50;
        -fx-text-fill: white;
        -fx-font-size: 14px;
        -fx-padding: 10px;
    }
    ```

---

### 5. **Testing**  

Perform comprehensive tests to ensure:  
- The functionality of each feature works as expected.  
- Database interactions (e.g., insert, update, delete, query) are reliable.  
- The user interface is intuitive, responsive, and visually appealing.  
![Login Page](https://github.com/hendhamdi/Gestion-Enseignants-Seances/blob/main/src/images/login.png)
![Register Page](https://github.com/hendhamdi/Gestion-Enseignants-Seances/blob/main/src/images/register.png)  
![enseignant Page](https://github.com/hendhamdi/Gestion-Enseignants-Seances/blob/main/src/images/enseignant.png)  
![seance Page](https://github.com/hendhamdi/Gestion-Enseignants-Seances/blob/main/src/images/seance.png) 

---

### **Conclusion**  

This project helps in mastering the use of JavaFX for creating graphical user interfaces and integrating Scene Builder for rapid and efficient visual design.  

The use of **MySQL** for data management and **FXML** for interface structure ensures a clear separation between the application's logic and its presentation, improving the maintainability and scalability of the codebase.  
