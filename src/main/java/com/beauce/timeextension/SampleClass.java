package com.beauce.timeextension;

import java.time.LocalDate;

public class SampleClass {
    public boolean isInTheFuture(LocalDate givenDate) {
        return LocalDate.now().isBefore(givenDate);
    }
}
