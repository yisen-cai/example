package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.*;

public class TestTemplateExample {
  final List<String> fruits = Arrays.asList("apple", "banana", "lemon");

  @TestTemplate
  @ExtendWith(MyTestTemplateInvocationContextProvider.class)
  void testTemplate(String fruit) {
    assertTrue(fruits.contains(fruit));
  }

  public static class MyTestTemplateInvocationContextProvider
      implements TestTemplateInvocationContextProvider {

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
      return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(
        ExtensionContext context) {
      return Stream.of(invocationContext("apple"), invocationContext("banana"));
    }

    private TestTemplateInvocationContext invocationContext(String parameter) {
      return new TestTemplateInvocationContext() {
        @Override
        public String getDisplayName(int invocationIndex) {
          return parameter;
        }

        @Override
        public List<Extension> getAdditionalExtensions() {
          return Collections.singletonList(
              new ParameterResolver() {
                @Override
                public boolean supportsParameter(
                    ParameterContext parameterContext, ExtensionContext extensionContext) {
                  return parameterContext.getParameter().getType().equals(String.class);
                }

                @Override
                public Object resolveParameter(
                    ParameterContext parameterContext, ExtensionContext extensionContext) {
                  return parameter;
                }
              });
        }
      };
    }
  }
}
