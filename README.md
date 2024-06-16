# WorkersReport

## Overview

`WorkersReport` is a Java-based command-line application that reads tasks from Excel files and provides various rankings based on the tasks data. The program can rank workers by total hours worked, rank months by total hours worked, and display the top 10 most productive days.

## Features

- Reads tasks from Excel files located in a specified directory.
- Ranks workers by total hours worked.
- Ranks months by total hours worked.
- Displays the top 10 most productive days.

## Dependencies

The program relies on the following libraries:

- Apache POI (for reading Excel files)

### Gradle Dependencies

Add the following dependencies to your `build.gradle` file:

```groovy
dependencies {
    implementation 'org.apache.poi:poi:5.2.3'
    implementation 'org.apache.poi:poi-ooxml:5.2.3'
    implementation 'org.apache.commons:commons-collections4:4.4'
}
```
## Setup
1. Clone the repository:
```
git clone https://github.com/your-username/WorkersReport.git
cd WorkersReport
```
2. Ensure you have JDK installed. You can download it from Oracle's official website.
3. Build the project using Gradle:
```
./gradlew build
```
## Running the Program
1. Navigate to the project directory.
2. Run the Main class:
```
./gradlew run
```
### Usage
1. Enter Directory Path:
```
Enter the directory path containing Excel files: /path/to/your-directory
```
2. Choose an Option
```
Choose an option:
1. Display ranking of workers by total hours worked
2. Display ranking of months by total hours worked
3. Display top 10 most productive days
4. Exit
```
### Author
Julian Haudek

