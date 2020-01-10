# 122BTestService
 Test Service for 122B MicroServices

## Running Tests

- First, deploy your microservice on your LOCAL machine using PORT 12345
- Run the service using one of the following:
~~~
[WINDOWS]         gradlew      test --tests TestClassName
[MACOS/UNIX]     ./gradlew     test --tests TestClassName

HW1:
 gradlew test --tests BasicTest
HW2:
 gradlew test --tests IdmTest
HW3 Individual Test:
 gradlew test --tests MovieTestBrowse
HW3 All Tests:
 gradlew test --tests MovieTest*
~~~

- This will run all tests named `TestClassName` and will output a html report and a xml report in a newly create report folder
- You will still need to delete the folder before every test, there is a commented out script in the gradlew.build folder that you can use to delete the folder automatically
