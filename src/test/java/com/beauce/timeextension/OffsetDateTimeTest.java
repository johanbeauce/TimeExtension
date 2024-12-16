package com.beauce.timeextension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OffsetDateTimeTest {

    SampleClass sampleClass;
    OffsetDateTime givenDateTime;

    @RegisterExtension
    TimeExtension timeExtension = new TimeExtension(OffsetDateTime.parse("2023-04-10T00:00:00Z"));

    @BeforeEach
    void setUp() {
        sampleClass = new SampleClass();
    }

    @Nested
    class Given_a_date_time_in_the_future {

        @BeforeEach
        void setUp() {
            givenDateTime = OffsetDateTime.parse("2023-04-11T00:00:00Z");
        }

        @Test
        void is_in_the_future_returns_true() {
            assertThat(sampleClass.isInTheFuture(givenDateTime)).isTrue();
        }
    }

    @Nested
    class Given_a_date_time_which_is_today {

        @BeforeEach
        void setUp() {
            givenDateTime = OffsetDateTime.parse("2023-04-10T00:00:00Z");
        }

        @Test
        void is_in_the_future_returns_true() {
            assertThat(sampleClass.isInTheFuture(givenDateTime)).isFalse();
        }
    }

    @Nested
    class Given_a_date_time_in_the_past {

        @BeforeEach
        void setUp() {
            givenDateTime = OffsetDateTime.parse("2023-04-09T00:00:00Z");
        }

        @Test
        void is_in_the_future_returns_false() {
            assertThat(sampleClass.isInTheFuture(givenDateTime)).isFalse();
        }
    }
}
