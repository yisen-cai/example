package com.glancebar.junit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Named.named;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.*;

public class ParameterizedTests {

  static Stream<String> stringProvider() {
    return Stream.of("apple", "banana");
  }

  static IntStream range() {
    return IntStream.range(0, 20).skip(10);
  }

  static Stream<Arguments> stringIntAndListProvider() {
    return Stream.of(
        arguments("apple", 1, Arrays.asList("a", "b")),
        arguments("lemon", 2, Arrays.asList("x", "y")));
  }

  @ParameterizedTest
  @ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
  public void palindromes(String candidate) {
    assertTrue(candidate.length() > 0);
  }

  @ParameterizedTest
  @NullSource
  @EmptySource
  @ValueSource(strings = {" ", "   ", "\t", "\n"})
  void nullEmptyAndBlankStrings(String text) {
    assertTrue(text == null || text.trim().isEmpty());
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "   ", "\t", "\n"})
  void nullEmptyAndBlankStringsWithComposedAnnotation(String text) {
    assertTrue(text == null || text.trim().isEmpty());
  }

  @ParameterizedTest
  @EnumSource(ChronoUnit.class)
  void testWithEnumSource(TemporalUnit unit) {
    assertNotNull(unit);
  }

  @ParameterizedTest
  @EnumSource
  void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
    assertNotNull(unit);
  }

  @ParameterizedTest
  @EnumSource(names = {"DAYS", "HOURS"})
  void testWithEnumSourceInclude(ChronoUnit unit) {
    assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
  }

  @ParameterizedTest
  @EnumSource(
      mode = EXCLUDE,
      names = {"ERAS", "FOREVER"})
  void testWithEnumSourceExclude(ChronoUnit unit) {
    assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
  }

  @ParameterizedTest
  @EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
  void testWithEnumSourceRegex(ChronoUnit unit) {
    assertTrue(unit.name().endsWith("DAYS"));
  }

  @ParameterizedTest
  @MethodSource("stringProvider")
  void testWithExplicitLocalMethodSource(String argument) {
    assertNotNull(argument);
  }

  @ParameterizedTest
  @MethodSource("range")
  void testWithRangeMethodSource(int argument) {
    assertNotEquals(9, argument);
  }

  @ParameterizedTest
  @MethodSource("stringIntAndListProvider")
  void testWithMultiArgMethodSource(String str, int num, List<String> list) {
    assertEquals(5, str.length());
    assertTrue(num >= 1 && num <= 2);
    assertEquals(2, list.size());
  }

  @ParameterizedTest
  @MethodSource("com.glancebar.junit.StringsProviders#tinyStrings")
  void testWithExternalMethodSource() {
    // test with tiny string
  }

  /**
   * CsvSource({ "apple, 'lemon, lime'" }) -> "apple", "lemon, lime" <br>
   * CsvSource({ "apple, ''" }) -> "apple", "" <br>
   * CsvSource({ "apple, " }) -> "apple", null <br>
   * CsvSource(value = { "apple, banana, NIL" }, nullValues = "NIL") -> <br>
   * CsvSource(value = { " apple , banana" }, ignoreLeadingAndTrailingWhitespace = false) -> <br>
   */
  @ParameterizedTest
  @CsvSource(
      useHeadersInDisplayName = true,
      value = {
        "Fruit,    Number",
        "apple,         1",
        "banana,        2",
        "'lemon, lime', 0xF1",
        "strawberry,    700_000"
      },
      delimiter = ',')
  void testWithCsvSource(String fruit, int rank) {
    assertNotNull(fruit);
    assertNotEquals(0, rank);
  }

  @ParameterizedTest
  @CsvSource(
      delimiter = '|',
      quoteCharacter = '"',
      textBlock =
          """
    #-----------------------------
    #    FRUIT     |     RANK
    #-----------------------------
         apple     |      1
    #-----------------------------
         banana    |      2
    #-----------------------------
      "lemon lime" |     0xF1
    #-----------------------------
       strawberry  |    700_000
    #-----------------------------
    """)
  void testWithCsvSourceWithTextBlock(String fruit, int rank) {
    // ...
  }

  @ParameterizedTest
  @CsvFileSource(useHeadersInDisplayName = true, resources = "/two-column.csv", numLinesToSkip = 0)
  void testWithCsvFileSourceFromClasspath(String country, int reference) {
    assertNotNull(country);
    assertNotEquals(0, reference);
  }

  @ParameterizedTest
  @ArgumentsSource(MyArgumentsProvider.class)
  void testWithArgumentsSource(String argument) {
    assertNotNull(argument);
  }

  static class MyArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
      return Stream.of("apple", "banana").map(Arguments::of);
    }
  }

  // ----------------------------------------------------------------------------------------------
  /** Fallback String-to-Object Conversion */
  @ParameterizedTest
  @ValueSource(strings = "42 Cats")
  void testWithImplicitFallbackArgumentConversion(Book book) {
    assertEquals("42 Cats", book.getTitle());
  }

  public static class Book {

    private String title;

    /** Invoked by factory method. */
    public static Book fromTitle(String title) {
      Book book = new Book();
      book.setTitle(title);
      return book;
    }

    public String getTitle() {
      return this.title;
    }

    public void setTitle(String title) {
      this.title = title;
    }
  }

  // ----------------------------------------------------------------------------------------------
  /** Custom argument converter. */
  @ParameterizedTest
  @EnumSource(ChronoUnit.class)
  void testWithExplicitArgumentConversion(
      @ConvertWith(ToStringArgumentConverter.class) String argument) {
    assertNotNull(ChronoUnit.valueOf(argument));
  }

  static class ToStringArgumentConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) {
      assertEquals(String.class, targetType, "Can only convert to String");
      if (source instanceof Enum<?>) {
        return ((Enum<?>) source).name();
      }
      return String.valueOf(source);
    }
  }

  /** Avoid boilerplate type checks. */
  public class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer> {

    protected ToLengthArgumentConverter() {
      super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) {
      return (source != null ? source.length() : 0);
    }
  }

  @ParameterizedTest
  @ValueSource(strings = {"01.01.2017", "31.12.2017"})
  void testWithExplicitJavaTimeConverter(
      @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
    assertEquals(2017, argument.getYear());
  }

  // ----------------------------------------------------------------------------------------------
  @ParameterizedTest
  @CsvSource({"Jane, Doe, F, 1990-05-20", "John, Doe, M, 1990-10-22"})
  void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
    Person person =
        new Person(
            arguments.getString(0),
            arguments.getString(1),
            arguments.get(2, Gender.class),
            arguments.get(3, LocalDate.class));

    if (person.getFirstName().equals("Jane")) {
      assertEquals(Gender.F, person.getGender());
    } else {
      assertEquals(Gender.M, person.getGender());
    }
    assertEquals("Doe", person.getLastName());
    assertEquals(1990, person.getDateOfBirth().getYear());
  }

  // ----------------------------------------------------------------------------------------------

  /** Custom Aggregator. */
  @ParameterizedTest
  @CsvSource({"Jane, Doe, F, 1990-05-20", "John, Doe, M, 1990-10-22"})
  void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person) {
    // perform assertions against person
  }

  //  @ParameterizedTest
  //  @CsvSource({
  //          "Jane, Doe, F, 1990-05-20",
  //          "John, Doe, M, 1990-10-22"
  //  })
  //  void testWithCustomAggregatorAnnotation(@CsvToPerson Person person) {
  //    // perform assertions against person
  //  }
  //
  //  @Retention(RetentionPolicy.RUNTIME)
  //  @Target(ElementType.PARAMETER)
  //  @AggregateWith(PersonAggregator.class)
  //  public @interface CsvToPerson {
  //  }

  static class PersonAggregator implements ArgumentsAggregator {
    @Override
    public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext context) {
      return new Person(
          arguments.getString(0),
          arguments.getString(1),
          arguments.get(2, Gender.class),
          arguments.get(3, LocalDate.class));
    }
  }

  static class Person {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Person(String firstName, String lastName, Gender gender, LocalDate dateOfBirth) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.dateOfBirth = dateOfBirth;
      this.gender = gender;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
      return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
      return gender;
    }

    public void setGender(Gender gender) {
      this.gender = gender;
    }
  }

  enum Gender {
    M(),
    F()
  }

  // ----------------------------------------------------------------------------------------------
  @DisplayName("Display name of container")
  @ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
  @CsvSource({"apple, 1", "banana, 2", "'lemon, lime', 3"})
  void testWithCustomDisplayNames(String fruit, int rank) {}

  @DisplayName("A parameterized test with named arguments")
  @ParameterizedTest(name = "{index}: {0}")
  @MethodSource("namedArguments")
  void testWithNamedArguments(File file) {}

  @BeforeEach
  void beforeEach(TestInfo testInfo) {
    // ...
  }

  @ParameterizedTest
  @ValueSource(strings = "apple")
  void testWithRegularParameterResolver(String argument, TestReporter testReporter) {
    testReporter.publishEntry("argument", argument);
  }

  @AfterEach
  void afterEach(TestInfo testInfo) {
    // ...
  }

  static Stream<Arguments> namedArguments() {
    return Stream.of(
        arguments(named("An important file", new File("path1"))),
        arguments(named("Another file", new File("path2"))));
  }
}

class StringsProviders {
  static Stream<String> tinyStrings() {
    return Stream.of(".", "oo", "OOO");
  }
}
