package com.beauce.timeextension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.mockito.MockedStatic;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static java.util.Objects.nonNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

public class TimeExtension implements InvocationInterceptor {
    private LocalDate fixedLocalDate;
    private OffsetDateTime fixedOffsetDateTime;

    public TimeExtension(LocalDate fixedLocalDate) {
        this.fixedLocalDate = fixedLocalDate;
    }

    public TimeExtension(OffsetDateTime offsetDateTime) {
        this.fixedOffsetDateTime = offsetDateTime;
    }

    public void changeDate(LocalDate fixedLocalDate) {
        this.fixedLocalDate = fixedLocalDate;
    }

    public void changeDateTime(OffsetDateTime fixedOffsetDateTime) {
        this.fixedOffsetDateTime = fixedOffsetDateTime;
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        try (MockedStatic<LocalDate> mockLocalDate = mockStatic(LocalDate.class, CALLS_REAL_METHODS);
             MockedStatic<OffsetDateTime> mockOffsetDateTime = mockStatic(OffsetDateTime.class, CALLS_REAL_METHODS);) {
            if (nonNull(fixedLocalDate)) {
                mockLocalDate.when(LocalDate::now).thenAnswer(call -> fixedLocalDate);
            }
            if (nonNull(fixedOffsetDateTime)) {
                mockOffsetDateTime.when(OffsetDateTime::now).thenAnswer(call -> fixedOffsetDateTime);
            }
            invocation.proceed();
        }
    }
}

