# Library Management System

## Overview
This is a Library Management System implemented in Java with an H2 Database for storage. The system allows users to manage books, students, and transactions (issue/return of books). It also includes secure user login and signup functionality.

The goal is to simplify library operations and provide a digital platform for efficient management.

### System Features
- **Authentication:**
  - Login for existing users
  - Signup for new users
- **Pages:** 
    - Home Page: Main navigation hub for accessing all features
    - Manage Books: Add, edit, delete, and view books in the library
    - Manage Students: Add, edit, delete, and view student records
    - Issue Book: Assign a book to a student
    - Issue Book Details: View all currently issued books
    - Return Book: Return a book and update availability
    - View Records: Display history of issued and returned books
    - Defaulter list: View a list of students who failed to return books on time.
- **Databases:**
    - Users – Stores login/signup credentials.
    - Book Details – Stores information about all books.
    - Student Details – Stores student records.
    - Issue Book Details – Stores data about issued and returned books.

## User Guide
1. **Starting the Application:**
   - The Login Page will appear. Use your credentials to log in or sign up if you are a new user. After login, the Home Page will provide access to all features.
   
2. **Home page:**
   - Provides links to all features.

3. **Managing Books:**
   - Add new books, update existing book information, or delete books.
   - View a list of all books in the library.

4. **Managing Students:**
   - Add new students, edit details, or remove student records.
   - View a list of all registered students.

5. **Issuing a Book:**
   - Use the Issue Book Details page to view all currently issued books.

6. **Returning a Book:**
    - Select the issued book to mark it as returned.

7. **Viewing Records:**
    - Open the View Records page to see the complete history of issued and returned books.

8. **Defaulter list:**
    - View a list of students who failed to return books on time.


### Javadoc Generation
The project's Javadoc documentation can be generated directly from the source code. To generate the Javadoc:
1. Open the terminal and navigate to the root directory of the project.
2. Run the following command:
   ```bash
   javadoc -d doc -sourcepath src -subpackages your.package.name
   ```
   This command will generate the HTML documentation for all classes and methods.

## Conclusion
This implementation of the Library Management System provides essential features for managing books and students. The system is extendable, allowing developers to add more advanced functionality and improve the overall experience.
