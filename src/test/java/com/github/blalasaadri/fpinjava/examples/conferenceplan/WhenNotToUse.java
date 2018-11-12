package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import org.immutables.value.Value;
import org.junit.jupiter.api.Test;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@SuppressWarnings("ALL")
class WhenNotToUse {

    @Test
    void whenNotToUse_whatNotHow() {
        Throwable throwableFromDeclarativeWithNull = catchThrowable(() -> numberOfAsIn_declarative(null));

        Throwable throwableFromDeclarativeWithNoAs = catchThrowable(() -> numberOfAsIn_declarative(""));

        assertSoftly(softly -> {
            softly.assertThat(throwableFromDeclarativeWithNull).as("declarative result from null")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Could not create result")
                    .hasCauseExactlyInstanceOf(IllegalInputException.class);

            softly.assertThat(throwableFromDeclarativeWithNoAs).as("declarative result from empty")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("No A's present")
                    .hasCauseExactlyInstanceOf(IllegalInputException.class);
        });
    }

    private Long numberOfAsIn_declarative(String input) {
        return Optional.of((Supplier<String>) () -> input)
                .map(valueSupplier -> {
                    try {
                        return createResult(true, valueSupplier.get());
                    } catch (IllegalInputException e) {
                        throw new IllegalArgumentException("Could not create result", e);
                    }
                })
                .filter(Result::isPresent)
                .map(Result::getValue)
                .map(value -> {
                    try {
                        return countA(value);
                    } catch (IllegalInputException e) {
                        throw new IllegalArgumentException("No A's present", e);
                    }
                })
                .orElse(0L);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    @Test
    void whenToUse_howNotWhat() {
        // Sometimes, procedural code is much easier to read than declarative code
        Throwable throwableFromProcedualWithNull = catchThrowable(() -> numberOfAsIn_procedual(null));

        Throwable throwableFromProcedualWithNoAs = catchThrowable(() -> numberOfAsIn_procedual(""));

        assertSoftly(softly -> {
            softly.assertThat(throwableFromProcedualWithNull).as("procedual result from null")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Could not create result")
                    .hasCauseExactlyInstanceOf(IllegalInputException.class);

            softly.assertThat(throwableFromProcedualWithNoAs).as("procedual result from empty")
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("No A's present")
                    .hasCauseExactlyInstanceOf(IllegalInputException.class);
        });
    }

    private Long numberOfAsIn_procedual(String input) {
        Result result;
        try {
            result = createResult(true, input);
        } catch (IllegalInputException e) {
            throw new IllegalArgumentException("Could not create result", e);
        }
        if (!result.isPresent()) {
            return 0L;
        }
        try {
            return countA(result.getValue());
        } catch (IllegalInputException e) {
            throw new IllegalArgumentException("No A's present", e);
        }
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    @Test
    void whenNotToUse_pureFunctions() {
        // When you WANT side effects, don't use pure functions. Duh.
        List.of("Hello", "World")
                .forEach(System.out::println);

        // Or when you want to get information from outside
        int answer = Optional.of(-3)
                .filter(number -> number > 0)
                .orElseGet(() -> getDefaultValue());

        assertThat(answer)
                .isPositive();
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    class NestedObject {
        private int value;
        private List<NestedObject> children;

        public NestedObject(int value, List<NestedObject> children) {
            this.value = value;
            this.children = children;
        }

        public int getValue() {
            return value;
        }

        public List<NestedObject> getChildren() {
            return children;
        }

        public void setChildren(List<NestedObject> children) {
            this.children = children;
        }
    }

    @Test
    void whenNotToUse_immutableValues() {
        List<NestedObject> nestedObjects = createNestedObjects();

        // Extract all value fields recursively
        io.vavr.collection.List<Integer> values = io.vavr.collection.List.ofAll(nestedObjects)
                .flatMap(this::flattenNestedObjects_withImmutableObjects)
                .map(NestedObject::getValue);

        assertThat(values)
                .containsExactlyInAnyOrder(11, 111, 112, 113);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private io.vavr.collection.List<NestedObject> flattenNestedObjects_withImmutableObjects(NestedObject nestedObject) {
        return io.vavr.collection.List.of(nestedObject)
                .appendAll(
                        io.vavr.collection.List.ofAll(nestedObject.getChildren())
                                .flatMap(this::flattenNestedObjects_withImmutableObjects)
                );
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    @Test
    void whenToUse_mutableValues() {
        List<NestedObject> nestedObjects = createNestedObjects();

        // Extract all value fields recursively
        List<Integer> values = nestedObjects.stream()
                .flatMap(nestedObject -> flattenNestedObjects_withMutableObjects(nestedObject).stream())
                .map(NestedObject::getValue)
                .collect(Collectors.toList());

        assertThat(values)
                .containsExactlyInAnyOrder(11, 111, 112, 113);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private List<NestedObject> flattenNestedObjects_withMutableObjects(NestedObject nestedObject) {
        List<NestedObject> seenBefore = new ArrayList<>();
        flattenNestedObjects_withMutableObjects(nestedObject, seenBefore);
        return Collections.unmodifiableList(seenBefore);
    }

    private void flattenNestedObjects_withMutableObjects(NestedObject nestedObject, List<NestedObject> seenBefore) {
        if (seenBefore.contains(nestedObject)) {
            return;
        }
        seenBefore.add(nestedObject);
        nestedObject.getChildren()
                .forEach(child -> flattenNestedObjects_withMutableObjects(child, seenBefore));
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    // End of the interesting stuff
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private Result createResult(boolean exists, String value) throws IllegalInputException {
        if (exists && value != null) {
            return ImmutableResult.builder().present(exists).value(value).build();
        } else if (!exists && value == null) {
            return ImmutableResult.builder().present(exists).build();
        } else {
            throw new IllegalInputException("Could not build a result with exists=" + exists + " and value='" + value + "'.");
        }
    }

    private long countA(String input) throws IllegalInputException {
        if (input.contains("a") || input.contains("A")) {
            return input.toUpperCase().chars()
                    .mapToObj(c -> (char) c)
                    .filter(c -> c == 'A')
                    .count();
        }
        throw new IllegalInputException("No A's present!!!");
    }

    @Value.Immutable
    @Value.Style(
            get = {"get*", "is*"}
    )
    interface Result {
        boolean isPresent();

        @Nullable
        String getValue();
    }

    private class IllegalInputException extends Exception {
        public IllegalInputException(String message) {
            super(message);
        }
    }

    private List<NestedObject> createNestedObjects() {
        NestedObject object11 = new NestedObject(
                11,
                List.of(
                        new NestedObject(111, List.of()),
                        new NestedObject(112, List.of()),
                        new NestedObject(113, List.of())
                ));
        List<NestedObject> childrenOf11 = new ArrayList<>(object11.getChildren());
        childrenOf11.add(object11);
        object11.setChildren(childrenOf11);
        return List.of(object11);
    }

    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    private int getDefaultValue() {
        return 42;
    }
}
