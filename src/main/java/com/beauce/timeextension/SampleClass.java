package com.beauce.timeextension;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class SampleClass {
    public boolean isInTheFuture(LocalDate givenDate) {
        return LocalDate.now().isBefore(givenDate);
    }

    public boolean isInTheFuture(OffsetDateTime givenDateTime) {
        return OffsetDateTime.now().isBefore(givenDateTime);
    }
}
