package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
public class WhatNotHowTests {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private TalkProvider talkProvider;

    @BeforeEach
    void setup() {
        talkProvider = new TalkProvider();
    }

    @Test
    void determineBestTalksForMonday() {
        List<ConferenceDay> conferenceDays = talkProvider.readConferenceDays();

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

        // Get talks on Monday
//        List<Slot> talksOnMonday = new ArrayList<>();
//        for (ConferenceDay conferenceDay : conferenceDays) {
//            List<Slot> slots = conferenceDay.getSlots();
//            for (Slot slot : slots) {
//                if (slot.getDay() == DayOfWeek.MONDAY) {
//                    Optional<Talk> optionalTalk = slot.getTalk();
//                    if (optionalTalk.isPresent()) {
//                        talksOnMonday.add(slot);
//                    }
//                }
//            }
//        }

        List<Slot> talksOnMonday = conferenceDays.stream()
                .flatMap(conferenceDay -> conferenceDay.getSlots().stream())
                .filter(slot -> slot.getDay() == DayOfWeek.MONDAY)
                .filter(slot -> slot.getTalk().isPresent())
                .collect(Collectors.toList());


        assertThat(talksOnMonday)
                .hasSize(33)
                .allMatch(slot -> slot.getDay() == DayOfWeek.MONDAY);

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

        // Rate talks
        List<RatedTalk> ratedTalksOnMonday = talkProvider.rateTalks(3, talksOnMonday);

        assertThat(ratedTalksOnMonday)
                .hasSameSizeAs(talksOnMonday);

        // Keep only the information we need
        List<MinimalTalkInfo> minimalTalksOnMonday = convertToMinimalTalkInfo(ratedTalksOnMonday);

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

        // Sort talks into time slots
//        Map<String, List<MinimalTalkInfo>> talksOnMondayGroupedByTimeslot = new HashMap<>();
//        for (MinimalTalkInfo talk : minimalTalksOnMonday) {
//            String timeSlot = formatTimeSlot(talk);
//            if (talksOnMondayGroupedByTimeslot.containsKey(timeSlot)) {
//                talksOnMondayGroupedByTimeslot
//                        .get(timeSlot)
//                        .add(talk);
//            } else {
//                List<MinimalTalkInfo> talksForTimeslot = new ArrayList<>();
//                talksForTimeslot.add(talk);
//                talksOnMondayGroupedByTimeslot.put(timeSlot, talksForTimeslot);
//            }
//        }

        Map<String, List<MinimalTalkInfo>> talksOnMondayGroupedByTimeslot =
                minimalTalksOnMonday.stream()
                        .collect(Collectors.toMap(
                                WhatNotHowTests::formatTimeSlot,
                                List::of,
                                (first, second) -> {
                                    List<MinimalTalkInfo> talks = new ArrayList<>(first);
                                    talks.addAll(second);
                                    return talks;
                                }
                        ));

        {
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("09:30-12:30"))
                    .as("09:30-12:30")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("Implementing Microservices with Jakarta EE and MicroProfile", List.of("Ivar Grimstad", "Kevin Sutter"), LocalTime.of(9, 30), LocalTime.of(12, 30), 5),
                            MinimalTalkInfo.of("9 Steps to Awesome with Kubernetes", List.of("Burr Sutter"), LocalTime.of(9, 30), LocalTime.of(12, 30), 1),
                            MinimalTalkInfo.of("Deep dive into using GraalVM for Java and JavaScript developers", List.of("Oleg Šelajev", "Thomas Wuerthinger"), LocalTime.of(9, 30), LocalTime.of(12, 30), 1),
                            MinimalTalkInfo.of("Lambdas and Streams Master Class Part 1", List.of("José Paumard", "Stuart Marks"), LocalTime.of(9, 30), LocalTime.of(12, 30), 2),
                            MinimalTalkInfo.of("Switching to Gradle: maturity, performance, and pleasure!", List.of("Cédric Champeau", "Louis Jacomet"), LocalTime.of(9, 30), LocalTime.of(12, 30), 4),
                            MinimalTalkInfo.of("DeepLearning for Developers - Introduction to DeepLearning with Keras and TensorFlow", List.of("Romeo Kienzler"), LocalTime.of(9, 30), LocalTime.of(12, 30), 3),
                            MinimalTalkInfo.of("Next-Generation End-to-End Testing with Cypress.io", List.of("Amir Rustamzadeh"), LocalTime.of(9, 30), LocalTime.of(12, 30), 5)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("13:30-16:30"))
                    .as("13:30-16:30")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("Measuring and improving performance in Go", List.of("Valentin Deleplace"), LocalTime.of(13, 30), LocalTime.of(16, 30), 5),
                            MinimalTalkInfo.of("Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions", List.of("Ray Tsang", "Eric Bottard"), LocalTime.of(13, 30), LocalTime.of(16, 30), 5),
                            MinimalTalkInfo.of("Life of a packet through Istio", List.of("Matt Turner"), LocalTime.of(13, 30), LocalTime.of(16, 30), 2),
                            MinimalTalkInfo.of("Java Streams vs. Reactive Streams: Which, When, How, and Why?", List.of("Venkat Subramaniam"), LocalTime.of(13, 30), LocalTime.of(16, 30), 1),
                            MinimalTalkInfo.of("A journey to Build a Distributed Blockchain Application", List.of("Ahmad Gohar"), LocalTime.of(13, 30), LocalTime.of(16, 30), 3),
                            MinimalTalkInfo.of("Learn Micronaut: a reactive microservices framework for the JVM", List.of("Alvaro Sanchez-Mariscal"), LocalTime.of(13, 30), LocalTime.of(16, 30), 3),
                            MinimalTalkInfo.of("Hands-on Android Architecture & Architecture Components", List.of("Laurent Baresse", "Arnaud Giuliani"), LocalTime.of(13, 30), LocalTime.of(16, 30), 5)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("16:45-17:15"))
                    .as("16:45-17:15")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("Implementing Language Servers - the Good, the Bad, the Ugly", List.of("Martin Lippert"), LocalTime.of(16, 45), LocalTime.of(17, 15), 2),
                            MinimalTalkInfo.of("Tracing performance of your service calls with some help of Sleuth, Zipkin & ELK", List.of("Rafaëla Breed"), LocalTime.of(16, 45), LocalTime.of(17, 15), 2),
                            MinimalTalkInfo.of("Traefik: A Scalable and Highly Available Edge Router", List.of("Damien Duportal"), LocalTime.of(16, 45), LocalTime.of(17, 15), 2),
                            MinimalTalkInfo.of("Functional Exception Handling in Java with Vavr", List.of("Grzegorz Piwowarek"), LocalTime.of(16, 45), LocalTime.of(17, 15), 3),
                            MinimalTalkInfo.of("Crypto currency, data mining and machine learning, wining combo ?", List.of("Pierre Paci", "Antoine Detante"), LocalTime.of(16, 45), LocalTime.of(17, 15), 2)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("17:30-18:00"))
                    .as("17:30-18:00")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("Selenium Tests the Object Oriented Way", List.of("Corina Pip"), LocalTime.of(17, 30), LocalTime.of(18, 0), 5),
                            MinimalTalkInfo.of("Meet Micronaut: a reactive microservices framework for the JVM", List.of("Alvaro Sanchez-Mariscal"), LocalTime.of(17, 30), LocalTime.of(18, 0), 2),
                            MinimalTalkInfo.of("Dude, Where's My Build? Android CI/CD at Uber", List.of("Gautam Korlam"), LocalTime.of(17, 30), LocalTime.of(18, 0), 3),
                            MinimalTalkInfo.of("A Dozen Ways to Hack Your Brain to Write Fluently", List.of("Dan Allen"), LocalTime.of(17, 30), LocalTime.of(18, 0), 1),
                            MinimalTalkInfo.of("The Silence of the Lambs: Inspecting Source Code and Binaries, in Continuous Delivery Pipelines ", List.of("Michael Hüttermann"), LocalTime.of(17, 30), LocalTime.of(18, 0), 1)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("18:15-18:45"))
                    .as("18:15-18:45")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("GraphQL SPQR: The fastest route to GraphQL", List.of("Bojan Tomić"), LocalTime.of(18, 15), LocalTime.of(18, 45), 4),
                            MinimalTalkInfo.of("Exploring Java Heap Dumps", List.of("Ryan Cuprak", "Geertjan Wielenga"), LocalTime.of(18, 15), LocalTime.of(18, 45), 4),
                            MinimalTalkInfo.of("Power of cloud dev environments for your legacy projects", List.of("Stéphane Tournié", "Etienne Vrignaud"), LocalTime.of(18, 15), LocalTime.of(18, 45), 3),
                            MinimalTalkInfo.of("Functional Programming in Java - When, Why and How?", List.of("Alasdair Collinson"), LocalTime.of(18, 15), LocalTime.of(18, 45), 5),
                            MinimalTalkInfo.of("Firebase ML Kit : Machine Learning made easy", List.of("Harshit Dwivedi"), LocalTime.of(18, 15), LocalTime.of(18, 45), 2)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("19:00-20:00"))
                    .as("19:00-20:00")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("DevOps: State of the Union", List.of("Michael Hüttermann"), LocalTime.of(19, 0), LocalTime.of(20, 0), 4),
                            MinimalTalkInfo.of("JavaFX.next", List.of("Kevin Rushforth", "Johan Vos"), LocalTime.of(19, 0), LocalTime.of(20, 0), 4)
                    );
            softly.assertThat(talksOnMondayGroupedByTimeslot.get("20:00-21:00"))
                    .as("20:00-21:00")
                    .containsExactlyInAnyOrder(
                            MinimalTalkInfo.of("MicroProfile: Past, Present and Future ", List.of("Emily Jiang"), LocalTime.of(20, 0), LocalTime.of(21, 0), 1),
                            MinimalTalkInfo.of("Code style: what kind of coder are you", List.of("Brian Vermeer"), LocalTime.of(20, 0), LocalTime.of(21, 0), 3)
                    );
            softly.assertAll();
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

        // Find the best talks for each timeslot by rating and, if there are several with
        // the same rating in a timeslot, choose the one with the title which would come
        // earlier in the alphabet.
//        Map<String, MinimalTalkInfo> highestRatedTalksPerTimeslot = new HashMap<>();
//        for (Map.Entry<String, List<MinimalTalkInfo>> timeslotPerTimeslot : talksOnMondayGroupedByTimeslot.entrySet()) {
//            List<MinimalTalkInfo> talksInTimeslot = timeslotPerTimeslot.getValue();
//            MinimalTalkInfo highestRatedTalk = talksInTimeslot.get(0);
//            for (MinimalTalkInfo talk : talksInTimeslot) {
//                if (talk == highestRatedTalk) {
//                    continue;
//                }
//                if (talk.getRating() > highestRatedTalk.getRating()) {
//                    highestRatedTalk = talk;
//                    continue;
//                }
//                if (talk.getRating() == highestRatedTalk.getRating()
//                        && talk.getTitle().compareTo(highestRatedTalk.getTitle()) < 0) {
//                    highestRatedTalk = talk;
//                }
//            }
//            highestRatedTalksPerTimeslot.put(timeslotPerTimeslot.getKey(), highestRatedTalk);
//        }

        Map<String, MinimalTalkInfo> highestRatedTalksPerTimeslot =
                talksOnMondayGroupedByTimeslot.entrySet().stream()
                        .map(entry -> {
                            List<MinimalTalkInfo> talksAtTimeslot = entry.getValue();
                            Comparator<MinimalTalkInfo> byRating =
                                    Comparator.comparingInt(MinimalTalkInfo::getRating);
                            Comparator<MinimalTalkInfo> byTitle =
                                    Comparator.comparing(MinimalTalkInfo::getTitle)
                                            .reversed();
                            MinimalTalkInfo minimalTalkInfo = talksAtTimeslot.stream()
                                    .max(byRating.thenComparing(byTitle))
                                    .orElseThrow(() -> new NoSuchElementException("No talks in timeslot " + entry.getKey()));
                            return Map.entry(entry.getKey(), minimalTalkInfo);
                        }).collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

        assertThat(highestRatedTalksPerTimeslot)
                .hasSameSizeAs(talksOnMondayGroupedByTimeslot)
                .containsEntry("09:30-12:30", MinimalTalkInfo.of("Implementing Microservices with Jakarta EE and MicroProfile", List.of("Ivar Grimstad", "Kevin Sutter"), LocalTime.of(9, 30), LocalTime.of(12, 30), 5))
                .containsEntry("13:30-16:30", MinimalTalkInfo.of("Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions", List.of("Ray Tsang", "Eric Bottard"), LocalTime.of(13, 30), LocalTime.of(16, 30), 5))
                .containsEntry("16:45-17:15", MinimalTalkInfo.of("Functional Exception Handling in Java with Vavr", List.of("Grzegorz Piwowarek"), LocalTime.of(16, 45), LocalTime.of(17, 15), 3))
                .containsEntry("17:30-18:00", MinimalTalkInfo.of("Selenium Tests the Object Oriented Way", List.of("Corina Pip"), LocalTime.of(17, 30), LocalTime.of(18, 0), 5))
                .containsEntry("18:15-18:45", MinimalTalkInfo.of("Functional Programming in Java - When, Why and How?", List.of("Alasdair Collinson"), LocalTime.of(18, 15), LocalTime.of(18, 45), 5))
                .containsEntry("19:00-20:00", MinimalTalkInfo.of("DevOps: State of the Union", List.of("Michael Hüttermann"), LocalTime.of(19, 0), LocalTime.of(20, 0), 4))
                .containsEntry("20:00-21:00", MinimalTalkInfo.of("Code style: what kind of coder are you", List.of("Brian Vermeer"), LocalTime.of(20, 0), LocalTime.of(21, 0), 3));
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

    private static String formatTimeSlot(MinimalTalkInfo minimalTalkInfo) {
        return minimalTalkInfo.getStartTime().format(TIME_FORMATTER) + "-" + minimalTalkInfo.getEndTime().format(TIME_FORMATTER);
    }

    private List<MinimalTalkInfo> convertToMinimalTalkInfo(List<RatedTalk> ratedTalksOnMonday) {
        // Keep only the information we need
        List<MinimalTalkInfo> minimalTalksOnMonday = new ArrayList<>();
        for (RatedTalk ratedTalk : ratedTalksOnMonday) {
            Optional<Talk> optionalTalk = ratedTalk.getSlot().getTalk();
            if (optionalTalk.isPresent()) {
                Talk talk = optionalTalk.get();
                List<String> speakerNames = new ArrayList<>();
                for (Speaker speaker : talk.getSpeakers())
                    speakerNames.add(speaker.getName());
                minimalTalksOnMonday.add(MinimalTalkInfo.of(
                        talk.getTitle(),
                        speakerNames,
                        ratedTalk.getSlot().getFromTime(),
                        ratedTalk.getSlot().getToTime(),
                        ratedTalk.getRating()
                ));
            }
        }
        return minimalTalksOnMonday;
    }

}