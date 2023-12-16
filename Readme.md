# MoodVie

## Prerequisites

Make sure you have the following tools installed on your machine:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (Version 21.X.X)
- [Maven](https://maven.apache.org/download.cgi) (Version 3.9.X)

## Checking Java Version

Open a terminal (or Command Prompt on Windows) and enter the following command:

```bash
java -version
```

Make sure the Java version displayed is 21.X.X.


## Checking Maven Version

Enter the following command in the terminal:

```bash
mvn -v
```

Ensure the Maven version displayed is 3.9.X.

## Set JAVA_HOME

If not set already, set the JAVA_HOME environment variable. Example on Linux/Mac:

```bash
export JAVA_HOME=/path/to/your/java
export PATH=$JAVA_HOME/bin:$PATH
```

> Note: You have to add these lines on your ~/.bashrc file

On Windows, use the System Properties or set it in the command prompt:

```cmd
set JAVA_HOME=C:\path\to\your\java
set PATH=%JAVA_HOME%\bin;%PATH%
```

## Building and Running the Project
### 1. Clone the Repository

```bash
git clone https://github.com/bastian-albaut/MoodVie.git
cd MoodVie
```

### 2. Build the Project

```bash
mvn compile
```

### 3. Package the code

```bash
mvn package
```

### 3. Execute the project

```bash
java -jar target/MoodVieMaeven-1.0-SNAPSHOT.jar
```

### 4. Run the JavaFX application

```bash
mvn javafx:run
```
