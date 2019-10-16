# 122BTestService
 Test Service for 122B MicroServices

## Running Tests

- First, deploy your microservice on your LOCAL machine using PORT 12345
- Provide request/response models for the test
- Run the service using one of the following:
~~~
./gradlew.bat test --tests *TestClassName
./gradlew     test --tests *TestClassName

Example for HW1:
 gradlew test --tests BasicTest
~~~

- This will run all tests named `TestClassName` and will output a html report and a xml report in a newly create report folder
- Right now the folder needs to be deleted to run the test again, There is a gradle option that will be added after some testing that will automatically delete the folder before the test runs.
