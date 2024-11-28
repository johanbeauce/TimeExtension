package com.beauce.timeextension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SampleClassTest {

    SampleClass sampleClass;
    LocalDate givenDate;

    @BeforeEach
    void setUp() {
        sampleClass = new SampleClass();
    }

    @Nested
    class Given_a_date_in_the_future {

        @BeforeEach
        void setUp() {
            givenDate = LocalDate.parse("2025-01-01");
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
            givenDate = LocalDate.now();
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
            givenDate = LocalDate.parse("2020-01-01");
        }

        @Test
        void is_in_the_future_returns_false() {
            assertThat(sampleClass.isInTheFuture(givenDate)).isFalse();
        }
    }
}