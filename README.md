# afterPayCodingExercise
AfterPay Coding Exercise

## Languages and Tools
- Java
- Gradle
- JUnit

## Info
- Expecting Java , Gradle
- Tested it on MacOS
- Gradle 6.3
- JVM: 13.0.2 (Oracle Corporation 13.0.2+8)
- src/main/resources has the input files

## Linting
- google-java-format-gradle-plugin

Execute the task googleJavaFormat to format all *.java files in the project
 ./gradlew goJF

Execute the task verifyGoogleJavaFormat to verify that all *.java files are formatted properly
 ./gradlew verGJF

## Concepts
- Using HashMap to store transactions for a specified hashed Credit Card .
- Go with Concrete classes , until there is a requirement to extract stuff into interfaces.
      - Dependency Inversion

## Assumptions
- InMemory Operations
- No TimeZone Conversions required 
- Data provided is valid.
- Sliding 24hr window is ongoing i.e. specified date and time onward
    
## To Run the program
- ./gradlew run
- Check example.log for the logs

## To Run the tests
 -- ./gradlew clean test
 -- Reports : at relative path ` build/reports/tests/test/index.html ` 
 -- CC with transactions under the threshold
 -- CC with transactions over the threshold

## Drawbacks of the Solution in its current form
 - Have introduced a bit more complexity than required , inorder to make the solution extensible.
 - Need to figure out how to improve the complexity in TransactionValidator from O(n*m)
 - Need to add exception handling. (Time Constraint)

## Some resources used 
-- https://stackoverflow.com/questions/50368493/how-to-parse-yyyy-mm-ddthhmmss-sssxxx-date-format-to-simple-in-androidclea