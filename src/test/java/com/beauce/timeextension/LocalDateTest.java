package com.beauce.timeextension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
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
    class Given_a_date_in_the_past_or_today {

        @ParameterizedTest
        @ValueSource(strings = {"2023-04-09", "2023-04-10"})
        void is_in_the_future_returns_false(String date) {
            givenDate = LocalDate.parse(date);
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