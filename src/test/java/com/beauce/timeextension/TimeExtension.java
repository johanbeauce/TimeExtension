package com.beauce.timeextension;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.mockito.MockedStatic;

import java.lang.reflect.Method;
import java.time.LocalDate;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mockStatic;

public class TimeExtension implements InvocationInterceptor {
    private final LocalDate fixedDate;

    public TimeExtension(LocalDate fixedDate) {
        this.fixedDate = fixedDate;
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        try (MockedStatic<LocalDate> mock = mockStatic(LocalDate.class, CALLS_REAL_METHODS)) { // all other methods will be real
            mock.when(LocalDate::now).thenAnswer(call -> fixedDate); // = uses a -> to allow changes of date
            invocation.proceed();
        }
    }
}

