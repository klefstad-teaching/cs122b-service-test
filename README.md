# 122BTestService
 Test Service for 122B MicroServices

## Running Tests

~~~
./gradlew.bat test --tests *TestClassName
./gradlew     test --tests *TestClassName
~~~

- This will run all tests named `TestClassName` and will output a html report and a xml report in a newly create report folder
- Right now the folder needs to be deleted to run the test again, There is a gradle option that will be added after some testing that will automaticly delete the folder before the test runs.
