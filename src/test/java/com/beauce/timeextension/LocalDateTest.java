package com.beauce.timeextension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class LocalDateTest {

    SampleClass sampleClass;
    LocalDate givenDate;

    // will be executed before each test
    @RegisterExtension
    TimeExtension timeExtension = new TimeExtension(LocalDate.parse("2023-04-10"));

    @BeforeEach
    void setUp() {
        sampleClass = new SampleClass();
    }

    @Nested
    class Given_a_date_in_the_future {

        @BeforeEach
        void setUp() {
            givenDate = LocalDate.parse("2023-04-11");
        }

        @Test
        void is_in_the_future_returns_true() {
            assertThat(sampleClass.isInTheFuture(givenDate)).isTrue();
        }
    }

    @Nested
    class Given_a_date_which_is_today {

        @BeforeEach
        void setUp() {
            givenDate = LocalDate.parse("2023-04-10");
        }

        @Test
        void is_in_the_future_returns_true() {
            assertThat(sampleClass.isInTheFuture(givenDate)).isFalse();
        }
    }

    @Nested
    class Given_a_date_in_the_past {

        @BeforeEach
        void setUp() {
            givenDate = LocalDate.parse("2023-04-09");
        }

        @Test
        void is_in_the_future_returns_false() {
            assertThat(sampleClass.isInTheFuture(givenDate)).isFalse();
        }
    }

    @Nested
    class Given_another_date {

        @BeforeEach
        void setUp() {
            // so you can change the date with no impact on the other tests
            timeExtension.changeDate(LocalDate.parse("2023-04-12"));
            givenDate = LocalDate.parse("2023-04-11");
        }

        @Test
        void is_in_the_future_returns_false() {
            assertThat(sampleClass.isInTheFuture(givenDate)).isFalse();
        }
    }
}