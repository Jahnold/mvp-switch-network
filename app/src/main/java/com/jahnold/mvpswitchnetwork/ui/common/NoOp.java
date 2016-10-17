package com.jahnold.mvpswitchnetwork.ui.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.jahnold.mvpswitchnetwork.ui.common.Defaults.defaultValue;
import static java.lang.reflect.Proxy.newProxyInstance;

/**
 * Created by matthewarnold on 29/04/2016.
 *
 */
public class NoOp {

    private static final InvocationHandler DEFAULT_VALUE = new DefaultValueInvocationHandler();

    private NoOp() {
        // no instances
    }

    @SuppressWarnings("unchecked") public static <T> T of(Class<T> interfaceClass) {
        return (T) newProxyInstance(interfaceClass.getClassLoader(), new Class[] { interfaceClass },
                DEFAULT_VALUE);
    }

    private static class DefaultValueInvocationHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return defaultValue(method.getReturnType());
        }
    }
}
