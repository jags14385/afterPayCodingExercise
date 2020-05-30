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
      -  Then filter accordingly.
- Go with Concrete classes , until there is a requirement to extract stuff into interfaces.
      - Dependency Inversion

## Assumptions
- InMemory Operations
- Supply the origin time for the 24hr sliding window
- No TimeZone Conversions required 
    
## To Run the program

## To Run the tests
 -- CC with zero transactions
 -- CC with transactions under the threshold
 -- CC with transactions over the threshold
 -- CC with transactions over the threshold , but not in the time window

## Yet to Build
-- Hashmap + filter + tests.

## Drawbacks of the Solution in its current form:
-- Have introduced a bit more complexity than required , inorder to make the solution extensible.

## Resources used 
-- https://stackoverflow.com/questions/50368493/how-to-parse-yyyy-mm-ddthhmmss-sssxxx-date-format-to-simple-in-android