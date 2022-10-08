package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FactoryMethodParameterResolver {

  @RegisterExtension private static final IntegerResolver integerResolver = new IntegerResolver();

  /** Customize factory parameter resolver. */
  static Stream<Arguments> factoryMethodWithArguments(int quantity) {
    return Stream.of(arguments(quantity + " apples"), arguments(quantity + " lemons"));
  }

  @ParameterizedTest
  @MethodSource("factoryMethodWithArguments")
  void testWithFactoryMethodWithArguments(String argument) {
    assertTrue(argument.startsWith("2"));
  }

  static class IntegerResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(
        ParameterContext parameterContext, ExtensionContext extensionContext) {
      return parameterContext.getParameter().getType() == int.class;
    }

    @Override
    public Object resolveParameter(
        ParameterContext parameterContext, ExtensionContext extensionContext) {
      return 2;
    }
  }
}
