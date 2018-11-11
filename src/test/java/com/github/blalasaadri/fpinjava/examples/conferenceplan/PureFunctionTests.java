package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import org.assertj.core.presentation.Representation;
import org.assertj.core.presentation.StandardRepresentation;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
class PureFunctionTests {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private Map<String, MinimalTalkInfo> selectedTalks;

    @BeforeEach
    void setUp() {
        selectedTalks = new HashMap<>();
        selectedTalks.put("09:30-12:30", MinimalTalkInfo.of(
                "Implementing Microservices with Jakarta EE and MicroProfile",
                List.of("Ivar Grimstad", "Kevin Sutter"),
                LocalTime.of(9, 30),
                LocalTime.of(12, 30),
                5
        ));
        selectedTalks.put("13:30-16:30", MinimalTalkInfo.of(
                "Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions",
                List.of("Ray Tsang", "Eric Bottard"),
                LocalTime.of(13, 30),
                LocalTime.of(16, 30),
                5
        ));
        selectedTalks.put("16:45-17:15", MinimalTalkInfo.of(
                "Functional Exception Handling in Java with Vavr",
                List.of("Grzegorz Piwowarek"),
                LocalTime.of(16, 45),
                LocalTime.of(17, 15),
                3
        ));
        selectedTalks.put("17:30-18:00", MinimalTalkInfo.of(
                "Selenium Tests the Object Oriented Way",
                List.of("Corina Pip"),
                LocalTime.of(17, 30),
                LocalTime.of(18, 0),
                5
        ));
        selectedTalks.put("18:15-18:45", MinimalTalkInfo.of(
                "Functional Programming in Java - When, Why and How?",
                List.of("Alasdair Collinson"),
                LocalTime.of(18, 15),
                LocalTime.of(18, 45),
                5
        ));
        selectedTalks.put("19:00-20:00", MinimalTalkInfo.of(
                "DevOps: State of the Union",
                List.of("Michael Hüttermann"),
                LocalTime.of(19, 0),
                LocalTime.of(20, 0),
                4
        ));
        selectedTalks.put("20:00-21:00", MinimalTalkInfo.of(
                "Code style: what kind of coder are you",
                List.of("Brian Vermeer"),
                LocalTime.of(20, 0),
                LocalTime.of(21, 0),
                3
        ));
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

    @Test
    void replaceTalkInSchedule() {
        Schedule schedule = new Schedule(selectedTalks);
        MinimalTalkInfo replacementTalk =
                MinimalTalkInfo.of("JavaFX.next", List.of("Kevin Rushforth", "Johan Vos"), LocalTime.of(19, 0), LocalTime.of(20, 0), 4);

        // Replace the sixth talk
        Schedule newSchedule = switchTalk(schedule, "19:00-20:00", replacementTalk);

        Schedule finalSchedule = newSchedule != null ? newSchedule : schedule;

        assertThat(finalSchedule)
                .isNotNull();
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

    private static Schedule switchTalk(Schedule schedule, String time, MinimalTalkInfo replacementTalk) {
        if (Objects.equals(formatTimeSlot(schedule.getFirstTalk()), time)) {
            schedule.setFirstTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getSecondTalk()), time)) {
            schedule.setSecondTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getThirdTalk()), time)) {
            schedule.setThirdTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getFourthTalk()), time)) {
            schedule.setFourthTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getFifthTalk()), time)) {
            schedule.setFifthTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getSixthTalk()), time)) {
            schedule.setSixthTalk(replacementTalk);
        } else if (Objects.equals(formatTimeSlot(schedule.getSeventhTalk()), time)) {
            schedule.setSeventhTalk(replacementTalk);
        } else {
            throw new IllegalArgumentException("No talk in the time slot " + time);
        }
        return schedule;
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

    @Test
    void calculateAverages() {
        Duration averageDuration = Duration.ofMinutes(Math.round(
                talksAsList().stream()
                        .map(this::getLengthOfTalk)
                        .mapToLong(Duration::toMinutes)
                        .average()
                        .orElse(0d)
        ));
        Duration maxDuration = Duration.ofMinutes(
                talksAsList().stream()
                        .map(this::getLengthOfTalk)
                        .mapToLong(Duration::toMinutes)
                        .max()
                        .orElse(0L)
        );

        assertThat(averageDuration)
                .withRepresentation(durationRepresentation())
                .isGreaterThan(Duration.ZERO);
        assertThat(maxDuration)
                .withRepresentation(durationRepresentation())
                .isGreaterThan(Duration.ZERO);
        assertThat(averageDuration)
                .withRepresentation(durationRepresentation())
                .isLessThanOrEqualTo(maxDuration);
    }

    //
    //
    //
    //
    //
    //

    private Duration getLengthOfTalk(MinimalTalkInfo talk) {
        cleanup();
        return Duration.between(talk.getStartTime(), talk.getEndTime());
    }

    //
    //
    //
    //
    //
    //
    //

    private void cleanup() {
        selectedTalks.clear();
        MinimalTalkInfo favouriteTalk = MinimalTalkInfo.of(
                "Functional Programming in Java - When, Why and How?",
                List.of("Alasdair Collinson"),
                LocalTime.of(18, 15),
                LocalTime.of(18, 45),
                5
        );
        List.of("09:30-12:30", "13:30-16:30", "16:45-17:15", "17:30-18:00", "18:15-18:45", "19:00-20:00", "20:00-21:00")
                .forEach(time -> selectedTalks.put(time, favouriteTalk));
    }

    //
    //
    //
    //
    //
    //
    //
    //

    private List<MinimalTalkInfo> talksAsList() {
        return List.copyOf(selectedTalks.values())
                .stream()
                .sorted(Comparator.comparing(MinimalTalkInfo::getEndTime).reversed())
                .collect(Collectors.toList());
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

    @Test
    void pureFunctions() {
        List<MinimalTalkInfo> talks = talksAsList();

        Function<String, Stream<? extends Character>> toCharacterStream = title ->
                title
                        .chars()
                        .mapToObj(character -> (char) character);
        Predicate<Character> isLetter = character ->
                Character.toUpperCase(character) >= 'A'
                        && Character.toUpperCase(character) <= 'Z';
        Comparator<Character> reverseAlphabetCaseInsensitive = (first, second) ->
                Character.compare(
                        Character.toUpperCase(second),
                        Character.toUpperCase(first)
                );
        Comparator<Character> upperCaseBeforeLowerCase = Character::compare;

        //
        //
        //
        //
        //
        //
        //

        // Most functions on Streams, however, do not need side effects
        String characters = talks.stream()
                .map(MinimalTalkInfo::getTitle)
                .flatMap(toCharacterStream)
                .filter(isLetter)
                .sorted(
                        reverseAlphabetCaseInsensitive
                                .thenComparing(upperCaseBeforeLowerCase))
                .reduce(
                        "",
                        (string, character) -> string + character,
                        Strings::concat
                );

        assertThat(characters)
                .isEqualTo("yyyyyxWWWwwwwwVvvvvvvUuuuuuuuuTttttttttttttttttttttttSSSssssssssssrrrrrrrrrrrrrrPPppppppOOOooooooooooooooooooonnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnMMMmmmmmlllllllllKKkkJJJjIiiiiiiiiiiiiiiiiiiiiiiiiiiiHHhhhhhhhhgggggggFFFFfffffEEEeeeeeeeeeeeeeeeeeeeeeeeeeeDdddddddddddCCcccccccccccBbbAaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    //
    //
    //
    //
    //
    //
    //

    @Test
    void unpureFunctions() {
        List<MinimalTalkInfo> talks = talksAsList();

        // Some functions on Collections / Streams require Consumers

        // The only consumer that doesn't have a side effect is:
        talks.forEach(talk -> {
        });

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

        // So non-useless Consumers which will have side effects
        talks.forEach(System.out::println);

        Optional<MinimalTalkInfo> anyTalk = talks.stream()
                .peek(System.out::println)
                .findAny();

        assertThat(anyTalk)
                .isNotEmpty();
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

    class Schedule {
        private MinimalTalkInfo firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk;

        Schedule(Map<String, MinimalTalkInfo> plannedTalks) {
            firstTalk = plannedTalks.get("09:30-12:30");
            secondTalk = plannedTalks.get("13:30-16:30");
            thirdTalk = plannedTalks.get("16:45-17:15");
            fourthTalk = plannedTalks.get("17:30-18:00");
            fifthTalk = plannedTalks.get("18:15-18:45");
            sixthTalk = plannedTalks.get("19:00-20:00");
            seventhTalk = plannedTalks.get("20:00-21:00");
        }

        public MinimalTalkInfo getFirstTalk() {
            return firstTalk;
        }

        public void setFirstTalk(MinimalTalkInfo firstTalk) {
            this.firstTalk = firstTalk;
        }

        public MinimalTalkInfo getSecondTalk() {
            return secondTalk;
        }

        public void setSecondTalk(MinimalTalkInfo secondTalk) {
            this.secondTalk = secondTalk;
        }

        public MinimalTalkInfo getThirdTalk() {
            return thirdTalk;
        }

        public void setThirdTalk(MinimalTalkInfo thirdTalk) {
            this.thirdTalk = thirdTalk;
        }

        public MinimalTalkInfo getFourthTalk() {
            return fourthTalk;
        }

        public void setFourthTalk(MinimalTalkInfo fourthTalk) {
            this.fourthTalk = fourthTalk;
        }

        public MinimalTalkInfo getFifthTalk() {
            return fifthTalk;
        }

        public void setFifthTalk(MinimalTalkInfo fifthTalk) {
            this.fifthTalk = fifthTalk;
        }

        public MinimalTalkInfo getSixthTalk() {
            return sixthTalk;
        }

        public void setSixthTalk(MinimalTalkInfo sixthTalk) {
            this.sixthTalk = sixthTalk;
        }

        public MinimalTalkInfo getSeventhTalk() {
            return seventhTalk;
        }

        public void setSeventhTalk(MinimalTalkInfo seventhTalk) {
            this.seventhTalk = seventhTalk;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Schedule schedule = (Schedule) o;
            return Objects.equals(firstTalk, schedule.firstTalk) &&
                    Objects.equals(secondTalk, schedule.secondTalk) &&
                    Objects.equals(thirdTalk, schedule.thirdTalk) &&
                    Objects.equals(fourthTalk, schedule.fourthTalk) &&
                    Objects.equals(fifthTalk, schedule.fifthTalk) &&
                    Objects.equals(sixthTalk, schedule.sixthTalk) &&
                    Objects.equals(seventhTalk, schedule.seventhTalk);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }
    }

    private static String formatTimeSlot(MinimalTalkInfo minimalTalkInfo) {
        return minimalTalkInfo.getStartTime().format(TIME_FORMATTER) + "-" + minimalTalkInfo.getEndTime().format(TIME_FORMATTER);
    }

    private Representation durationRepresentation() {
        return new StandardRepresentation() {
            @Override
            public String toStringOf(Object object) {
                if (object instanceof Duration) {
                    return ((Duration) object).toMinutes() + " minutes";
                }
                return object.toString();
            }
        };
    }
}
