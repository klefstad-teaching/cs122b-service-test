# 122BTestService
 Test Service for 122B MicroServices

## Running Tests

- First, deploy your microservice on your LOCAL machine using PORT 12345
- Provide request/response models for the test
- Run the service using one of the following:
~~~
./gradlew.bat test --tests *TestClassName
./gradlew     test --tests *TestClassName

HW1:
 gradlew test --tests *BasicTest
HW2
 gradlew test --tests *IdmTest
~~~

- This will run all tests named `TestClassName` and will output a html report and a xml report in a newly create report folder
- You will still need to delete the folder before every test, there is a commented out script in the gradlew.build folder that you can use to delete the folder automaticly
