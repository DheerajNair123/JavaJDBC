# Java JDBC with H2 Database Setup Guide

## Overview
This guide provides step-by-step instructions for setting up Java JDBC connectivity with H2 database in your project.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven build tool
- IDE (IntelliJ IDEA, Eclipse, etc.)

## Project Structure
```
your-project/
├── pom.xml
├── src/
│   └── main/
│       └── java/
│           └── TestDB.java
└── README.md
```

## Setup Instructions

### Step 1: Configure Maven Dependencies

Add the H2 database dependency to your `pom.xml` file:

```xml
<project>
    <!-- Other project configurations -->
    
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.3.232</version>
        </dependency>
    </dependencies>
</project>
```

### Step 2: Database Connection Configuration

In your `TestDB.java` file, configure the database connection:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    // Database connection parameters
    private static final String DB_URL = "jdbc:h2:file:C:\\Users\\dheer\\IdeaProjects\\Bookstore";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    
    public static void main(String[] args) {
        try {
            // Establish connection
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connected successfully!");
            
            // Close connection
            connection.close();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
```

### Step 3: Customize File Path

**Important**: Update the database file path in `TestDB.java` to match your project location:

```java
// Replace this path with your actual project path
private static final String DB_URL = "jdbc:h2:file:YOUR_PROJECT_PATH_HERE";
```

**Example paths:**
- Windows: `"jdbc:h2:file:C:\\Users\\username\\IdeaProjects\\ProjectName"`
- macOS/Linux: `"jdbc:h2:file:/Users/username/IdeaProjects/ProjectName"`

## H2 Database Connection URL Formats

### File-based Database (Recommended for development)
```java
"jdbc:h2:file:./database/bookstore"     // Relative path
"jdbc:h2:file:C:/data/bookstore"        // Absolute path (Windows)
"jdbc:h2:file:/home/user/data/bookstore" // Absolute path (Linux/macOS)
```

### In-memory Database (for testing)
```java
"jdbc:h2:mem:testdb"                    // In-memory database
```

## Common Issues and Solutions

### Issue 1: ClassNotFoundException
**Problem**: `java.lang.ClassNotFoundException: org.h2.Driver`
**Solution**: 
- Ensure H2 dependency is added to `pom.xml`
- Run `mvn clean install` to download dependencies
- Refresh/reimport Maven project in your IDE
