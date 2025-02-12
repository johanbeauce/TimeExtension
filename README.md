
# How do you test your code when it depends on time?
I propose you a little exercise that you can during a brown bag lunch or a dev 2 dev session. 

Try to cover the following code with unit tests.

```java
public class SampleClass {
    public boolean isInTheFuture(LocalDate givenDate) {
        return LocalDate.now().isBefore(givenDate);
    }

    public boolean isInTheFuture(OffsetDateTime givenDateTime) {
        return OffsetDateTime.now().isBefore(givenDateTime);
    }
}
```
It's interesting how you can test the `isInTheFuture` method. 

# Proposed solution
A solution is available in this repository.

LocalDateTest and OffsetDateTimeTest are the test classes that you can use to test the SampleClass using a custom extension with JUnit 5.
