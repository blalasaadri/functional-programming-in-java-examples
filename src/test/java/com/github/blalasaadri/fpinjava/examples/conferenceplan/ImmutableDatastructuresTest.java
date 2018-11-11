package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import org.immutables.value.Value;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("ALL")
class ImmutableDatastructuresTest {

    class TalkAndNotes implements Cloneable {
        private MinimalTalkInfo talkInfo;
        private String notes;

        public TalkAndNotes(MinimalTalkInfo talkInfo, String notes) {
            this.talkInfo = talkInfo;
            this.notes = notes;
        }

        public MinimalTalkInfo getTalkInfo() {
            return talkInfo;
        }

        public void setTalkInfo(MinimalTalkInfo talkInfo) {
            this.talkInfo = talkInfo;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TalkAndNotes that = (TalkAndNotes) o;
            return Objects.equals(talkInfo, that.talkInfo) &&
                    Objects.equals(notes, that.notes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(talkInfo, notes);
        }

        @Override
        public String toString() {
            return "TalkAndNotes{" +
                    "talkInfo=" + talkInfo +
                    ", notes='" + notes + '\'' +
                    '}';
        }

        public TalkAndNotes copy() {
            try {
                return (TalkAndNotes) clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }

    class Schedule implements Cloneable {

        private TalkAndNotes firstTalk;
        private TalkAndNotes secondTalk;
        private TalkAndNotes thirdTalk;
        private TalkAndNotes fourthTalk;
        private TalkAndNotes fifthTalk;
        private TalkAndNotes sixthTalk;
        private TalkAndNotes seventhTalk;

        public TalkAndNotes getFirstTalk() {
            return firstTalk;
        }

        public void setFirstTalk(TalkAndNotes firstTalk) {
            this.firstTalk = firstTalk;
        }

        public void setFirstTalk(MinimalTalkInfo talk, String notes) {
            this.firstTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getSecondTalk() {
            return secondTalk;
        }

        public void setSecondTalk(TalkAndNotes secondTalk) {
            this.secondTalk = secondTalk;
        }

        public void setSecondTalk(MinimalTalkInfo talk, String notes) {
            this.secondTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getThirdTalk() {
            return thirdTalk;
        }

        public void setThirdTalk(TalkAndNotes thirdTalk) {
            this.thirdTalk = thirdTalk;
        }

        public void setThirdTalk(MinimalTalkInfo talk, String notes) {
            this.thirdTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getFourthTalk() {
            return fourthTalk;
        }

        public void setFourthTalk(TalkAndNotes fourthTalk) {
            this.fourthTalk = fourthTalk;
        }

        public void setFourthTalk(MinimalTalkInfo talk, String notes) {
            this.fourthTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getFifthTalk() {
            return fifthTalk;
        }

        public void setFifthTalk(TalkAndNotes fifthTalk) {
            this.fifthTalk = fifthTalk;
        }

        public void setFifthTalk(MinimalTalkInfo talk, String notes) {
            this.fifthTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getSixthTalk() {
            return sixthTalk;
        }

        public void setSixthTalk(TalkAndNotes sixthTalk) {
            this.sixthTalk = sixthTalk;
        }

        public void setSixthTalk(MinimalTalkInfo talk, String notes) {
            this.sixthTalk = new TalkAndNotes(talk, notes);
        }

        public TalkAndNotes getSeventhTalk() {
            return seventhTalk;
        }

        public void setSeventhTalk(TalkAndNotes seventhTalk) {
            this.seventhTalk = seventhTalk;
        }

        public void setSeventhTalk(MinimalTalkInfo talk, String notes) {
            this.seventhTalk = new TalkAndNotes(talk, notes);
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

        public Schedule copy() {
            try {
                return (Schedule) clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }
    }

    private Schedule createSchedule() {
        Schedule mySchedule = new Schedule();
        mySchedule.setFirstTalk(MinimalTalkInfo.of(
                "Implementing Microservices with Jakarta EE and MicroProfile",
                List.of("Ivar Grimstad", "Kevin Sutter"),
                LocalTime.of(9, 30),
                LocalTime.of(12, 30),
                5
        ), "");
        mySchedule.setSecondTalk(MinimalTalkInfo.of(
                "Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions",
                List.of("Ray Tsang", "Eric Bottard"),
                LocalTime.of(13, 30),
                LocalTime.of(16, 30),
                5
        ), "");
        mySchedule.setThirdTalk(MinimalTalkInfo.of(
                "Functional Exception Handling in Java with Vavr",
                List.of("Grzegorz Piwowarek"),
                LocalTime.of(16, 45),
                LocalTime.of(17, 15),
                3
        ), "");
        mySchedule.setFourthTalk(MinimalTalkInfo.of(
                "Selenium Tests the Object Oriented Way",
                List.of("Corina Pip"),
                LocalTime.of(17, 30),
                LocalTime.of(18, 0),
                5
        ), "");
        mySchedule.setFifthTalk(MinimalTalkInfo.of(
                "Functional Programming in Java - When, Why and How?",
                List.of("Alasdair Collinson"),
                LocalTime.of(18, 15),
                LocalTime.of(18, 45),
                5
        ), "");
        mySchedule.setSixthTalk(MinimalTalkInfo.of(
                "DevOps: State of the Union",
                List.of("Michael Hüttermann"),
                LocalTime.of(19, 0),
                LocalTime.of(20, 0),
                4
        ), "");
        mySchedule.setSeventhTalk(MinimalTalkInfo.of(
                "Code style: what kind of coder are you",
                List.of("Brian Vermeer"),
                LocalTime.of(20, 0),
                LocalTime.of(21, 0),
                3
        ), "");
        return mySchedule;
    }

    @Test
    void showScheduleToAFriendAndThenVisitYourFirstTalkForTheDay() {
        Schedule mySchedule = createSchedule();
        Schedule friendsSchedule = showScheduleToAFriend(mySchedule);

        String myActionDescription = visitFirstTalkOf(mySchedule);
        String friendsActionDescription = visitFirstTalkOf(friendsSchedule);

        assertThat(myActionDescription)
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Implementing Microservices with Jakarta EE and MicroProfile\" " +
                        "by Ivar Grimstad and Kevin Sutter.");
        assertThat(friendsActionDescription)
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Lambdas and Streams Master Class Part 1\" " +
                        "by José Paumard and Stuart Marks.");
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

    public Schedule showScheduleToAFriend(Schedule schedule) {
        // Hey, that's a great schedule! I want to visit the same talks, expept for the first one.
        Schedule friendsSchedule = schedule.copy();
        TalkAndNotes firstTalk = friendsSchedule.getFirstTalk().copy();
        firstTalk.setTalkInfo(MinimalTalkInfo.of(
                "Lambdas and Streams Master Class Part 1",
                List.of("José Paumard", "Stuart Marks"),
                LocalTime.of(9, 30),
                LocalTime.of(12, 30),
                2
        ));
        friendsSchedule.setFirstTalk(firstTalk);
        return friendsSchedule;
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

    class BetterTalkAndNotes {
        private final MinimalTalkInfo talkInfo;
        private final String notes;

        public BetterTalkAndNotes(MinimalTalkInfo talkInfo, String notes) {
            this.talkInfo = talkInfo;
            this.notes = notes;
        }

        public MinimalTalkInfo getTalkInfo() {
            return talkInfo;
        }

        public BetterTalkAndNotes withTalkInfo(MinimalTalkInfo talkInfo) {
            return new BetterTalkAndNotes(talkInfo, notes);
        }

        public String getNotes() {
            return notes;
        }

        public BetterTalkAndNotes withNotes(String notes) {
            return new BetterTalkAndNotes(talkInfo, notes);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BetterTalkAndNotes that = (BetterTalkAndNotes) o;
            return Objects.equals(talkInfo, that.talkInfo) &&
                    Objects.equals(notes, that.notes);
        }

        @Override
        public int hashCode() {
            return Objects.hash(talkInfo, notes);
        }

        @Override
        public String toString() {
            return "BetterTalkAndNotes{" +
                    "talkInfo=" + talkInfo +
                    ", notes='" + notes + '\'' +
                    '}';
        }
    }

    class BetterSchedule {
        private final BetterTalkAndNotes firstTalk;
        private final BetterTalkAndNotes secondTalk;
        private final BetterTalkAndNotes thirdTalk;
        private final BetterTalkAndNotes fourthTalk;
        private final BetterTalkAndNotes fifthTalk;
        private final BetterTalkAndNotes sixthTalk;
        private final BetterTalkAndNotes seventhTalk;

        public BetterSchedule(
                BetterTalkAndNotes firstTalk, BetterTalkAndNotes secondTalk, BetterTalkAndNotes thirdTalk,
                BetterTalkAndNotes fourthTalk, BetterTalkAndNotes fifthTalk, BetterTalkAndNotes sixthTalk,
                BetterTalkAndNotes seventhTalk
        ) {
            this.firstTalk = firstTalk;
            this.secondTalk = secondTalk;
            this.thirdTalk = thirdTalk;
            this.fourthTalk = fourthTalk;
            this.fifthTalk = fifthTalk;
            this.sixthTalk = sixthTalk;
            this.seventhTalk = seventhTalk;
        }

        public BetterSchedule() {
            this(null, null, null, null, null, null, null);
        }

        public BetterTalkAndNotes getFirstTalk() {
            return firstTalk;
        }

        public BetterSchedule withFirstTalk(BetterTalkAndNotes firstTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withFirstTalk(MinimalTalkInfo talk, String notes) {
            return this.withFirstTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getSecondTalk() {
            return secondTalk;
        }

        public BetterSchedule withSecondTalk(BetterTalkAndNotes secondTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withSecondTalk(MinimalTalkInfo talk, String notes) {
            return this.withSecondTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getThirdTalk() {
            return thirdTalk;
        }

        public BetterSchedule withThirdTalk(BetterTalkAndNotes thirdTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withThirdTalk(MinimalTalkInfo talk, String notes) {
            return this.withThirdTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getFourthTalk() {
            return fourthTalk;
        }

        public BetterSchedule withFourthTalk(BetterTalkAndNotes fourthTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withFourthTalk(MinimalTalkInfo talk, String notes) {
            return this.withFourthTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getFifthTalk() {
            return fifthTalk;
        }

        public BetterSchedule withFifthTalk(BetterTalkAndNotes fifthTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withFifthTalk(MinimalTalkInfo talk, String notes) {
            return this.withFifthTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getSixthTalk() {
            return sixthTalk;
        }

        public BetterSchedule withSixthTalk(BetterTalkAndNotes sixthTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withSixthTalk(MinimalTalkInfo talk, String notes) {
            return this.withSixthTalk(new BetterTalkAndNotes(talk, notes));
        }

        public BetterTalkAndNotes getSeventhTalk() {
            return seventhTalk;
        }

        public BetterSchedule withSeventhTalk(BetterTalkAndNotes seventhTalk) {
            return new BetterSchedule(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }

        public BetterSchedule withSeventhTalk(MinimalTalkInfo talk, String notes) {
            return this.withSeventhTalk(new BetterTalkAndNotes(talk, notes));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BetterSchedule that = (BetterSchedule) o;
            return Objects.equals(firstTalk, that.firstTalk) &&
                    Objects.equals(secondTalk, that.secondTalk) &&
                    Objects.equals(thirdTalk, that.thirdTalk) &&
                    Objects.equals(fourthTalk, that.fourthTalk) &&
                    Objects.equals(fifthTalk, that.fifthTalk) &&
                    Objects.equals(sixthTalk, that.sixthTalk) &&
                    Objects.equals(seventhTalk, that.seventhTalk);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstTalk, secondTalk, thirdTalk, fourthTalk, fifthTalk, sixthTalk, seventhTalk);
        }
    }

    private BetterSchedule createBetterSchedule() {
        BetterSchedule myBetterSchedule = new BetterSchedule()
                .withFirstTalk(MinimalTalkInfo.of(
                        "Implementing Microservices with Jakarta EE and MicroProfile",
                        List.of("Ivar Grimstad", "Kevin Sutter"),
                        LocalTime.of(9, 30),
                        LocalTime.of(12, 30),
                        5
                ), "")
                .withSecondTalk(MinimalTalkInfo.of(
                        "Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions",
                        List.of("Ray Tsang", "Eric Bottard"),
                        LocalTime.of(13, 30),
                        LocalTime.of(16, 30),
                        5
                ), "")
                .withThirdTalk(MinimalTalkInfo.of(
                        "Functional Exception Handling in Java with Vavr",
                        List.of("Grzegorz Piwowarek"),
                        LocalTime.of(16, 45),
                        LocalTime.of(17, 15),
                        3
                ), "")
                .withFourthTalk(MinimalTalkInfo.of(
                        "Selenium Tests the Object Oriented Way",
                        List.of("Corina Pip"),
                        LocalTime.of(17, 30),
                        LocalTime.of(18, 0),
                        5
                ), "")
                .withFifthTalk(MinimalTalkInfo.of(
                        "Functional Programming in Java - When, Why and How?",
                        List.of("Alasdair Collinson"),
                        LocalTime.of(18, 15),
                        LocalTime.of(18, 45),
                        5
                ), "")
                .withSixthTalk(MinimalTalkInfo.of(
                        "DevOps: State of the Union",
                        List.of("Michael Hüttermann"),
                        LocalTime.of(19, 0),
                        LocalTime.of(20, 0),
                        4
                ), "")
                .withSeventhTalk(MinimalTalkInfo.of(
                        "Code style: what kind of coder are you",
                        List.of("Brian Vermeer"),
                        LocalTime.of(20, 0),
                        LocalTime.of(21, 0),
                        3
                ), "");
        return myBetterSchedule;
    }

    @Test
    void showBetterScheduleToAFriendAndThenVisitYourFirstTalkForTheDay() {
        BetterSchedule myBetterSchedule = createBetterSchedule();
        BetterSchedule friendsSchedule = showScheduleToAFriend(myBetterSchedule);

        String yourActionDescription = visitFirstTalkOf(myBetterSchedule);
        String friendsActionDescription = visitFirstTalkOf(friendsSchedule);

        assertThat(yourActionDescription)
                .as("your action description")
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Implementing Microservices with Jakarta EE and MicroProfile\" " +
                        "by Ivar Grimstad and Kevin Sutter.");
        assertThat(friendsActionDescription)
                .as("firends action description")
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Lambdas and Streams Master Class Part 1\" " +
                        "by José Paumard and Stuart Marks.");
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

    public BetterSchedule showScheduleToAFriend(BetterSchedule schedule) {
        // Hey, that's a great schedule! I want to visit the same talks, expept for the first one.
        BetterTalkAndNotes firstTalk = schedule.getFirstTalk()
                .withTalkInfo(MinimalTalkInfo.of(
                        "Lambdas and Streams Master Class Part 1",
                        List.of("José Paumard", "Stuart Marks"),
                        LocalTime.of(9, 30),
                        LocalTime.of(12, 30),
                        2
                ));
        return schedule.withFirstTalk(firstTalk);
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

    @Value.Immutable
    @Value.Style(
            deepImmutablesDetection = true,
            allParameters = true
    )
    public interface BestTalkAndNotes {
        MinimalTalkInfo getTalkInfo();

        BestTalkAndNotes withTalkInfo(MinimalTalkInfo talkInfo);

        String getNotes();
    }

    @Value.Immutable
    @Value.Style(
            deepImmutablesDetection = true,
            allParameters = true
    )
    public interface BestSchedule {
        BestTalkAndNotes getFirstTalk();

        BestSchedule withFirstTalk(BestTalkAndNotes firstTalk);

        BestTalkAndNotes getSecondTalk();

        BestSchedule withSecondTalk(BestTalkAndNotes secondTalk);

        BestTalkAndNotes getThirdTalk();

        BestSchedule withThirdTalk(BestTalkAndNotes thirdTalk);

        BestTalkAndNotes getFourthTalk();

        BestSchedule withFourthTalk(BestTalkAndNotes fourthTalk);

        BestTalkAndNotes getFifthTalk();

        BestSchedule withFifthTalk(BestTalkAndNotes fifthTalk);

        BestTalkAndNotes getSixthTalk();

        BestSchedule withSixthTalk(BestTalkAndNotes sixthTalk);

        BestTalkAndNotes getSeventhTalk();

        BestSchedule withSeventhTalk(BestTalkAndNotes seventhTalk);
    }

    private BestSchedule createBestSchedule() {
        BestSchedule myBestSchedule = ImmutableBestSchedule.builder()
                .firstTalk(MinimalTalkInfo.of(
                        "Implementing Microservices with Jakarta EE and MicroProfile",
                        List.of("Ivar Grimstad", "Kevin Sutter"),
                        LocalTime.of(9, 30),
                        LocalTime.of(12, 30),
                        5
                ), "")
                .secondTalk(MinimalTalkInfo.of(
                        "Beyond Kubernetes - Managing Applications and Functions with Knative, riff, and Spring Cloud Functions",
                        List.of("Ray Tsang", "Eric Bottard"),
                        LocalTime.of(13, 30),
                        LocalTime.of(16, 30),
                        5
                ), "")
                .thirdTalk(MinimalTalkInfo.of(
                        "Functional Exception Handling in Java with Vavr",
                        List.of("Grzegorz Piwowarek"),
                        LocalTime.of(16, 45),
                        LocalTime.of(17, 15),
                        3
                ), "")
                .fourthTalk(MinimalTalkInfo.of(
                        "Selenium Tests the Object Oriented Way",
                        List.of("Corina Pip"),
                        LocalTime.of(17, 30),
                        LocalTime.of(18, 0),
                        5
                ), "")
                .fifthTalk(MinimalTalkInfo.of(
                        "Functional Programming in Java - When, Why and How?",
                        List.of("Alasdair Collinson"),
                        LocalTime.of(18, 15),
                        LocalTime.of(18, 45),
                        5
                ), "")
                .sixthTalk(MinimalTalkInfo.of(
                        "DevOps: State of the Union",
                        List.of("Michael Hüttermann"),
                        LocalTime.of(19, 0),
                        LocalTime.of(20, 0),
                        4
                ), "")
                .seventhTalk(MinimalTalkInfo.of(
                        "Code style: what kind of coder are you",
                        List.of("Brian Vermeer"),
                        LocalTime.of(20, 0),
                        LocalTime.of(21, 0),
                        3
                ), "")
                .build();
        return myBestSchedule;
    }

    @Test
    void showBestScheduleToAFriendAndThenVisitYourFirstTalkForTheDay() {
        BestSchedule myBestSchedule = createBestSchedule();
        BestSchedule friendsSchedule = showScheduleToAFriend(myBestSchedule);

        String yourActionDescription = visitFirstTalkOf(myBestSchedule);
        String friendsActionDescription = visitFirstTalkOf(friendsSchedule);

        assertThat(yourActionDescription)
                .as("your action description")
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Implementing Microservices with Jakarta EE and MicroProfile\" " +
                        "by Ivar Grimstad and Kevin Sutter.");
        assertThat(friendsActionDescription)
                .as("firends action description")
                .isEqualTo("You are now visiting your first talk for the day: " +
                        "\"Lambdas and Streams Master Class Part 1\" " +
                        "by José Paumard and Stuart Marks.");
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

    public BestSchedule showScheduleToAFriend(BestSchedule schedule) {
        // Hey, that's a great schedule! I want to visit the same talks, expept for the first one.
        BestTalkAndNotes firstTalk = schedule.getFirstTalk()
                .withTalkInfo(MinimalTalkInfo.of(
                        "Lambdas and Streams Master Class Part 1",
                        List.of("José Paumard", "Stuart Marks"),
                        LocalTime.of(9, 30),
                        LocalTime.of(12, 30),
                        2
                ));
        return schedule.withFirstTalk(firstTalk);
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

    @Value.Immutable
    @Value.Style(
            deepImmutablesDetection = true,
            allParameters = true
    )
    interface Schedules {
        io.vavr.collection.List<BestSchedule> getSchedules();

        Schedules withSchedules(io.vavr.collection.List<BestSchedule> schedules);
    }

    @Test
    void modifyingMutableValuesWithinImmutables() {
        io.vavr.collection.List<BestSchedule> immmutableSchedules = io.vavr.collection.List.empty();

        Schedules schedules = ImmutableSchedules.builder()
                .schedules(immmutableSchedules)
                .build();

        var schedulesWithBestSchedule = schedules.getSchedules()
                .append(createBestSchedule());

        Schedules newSchedules = schedules.withSchedules(schedulesWithBestSchedule);

        assertThat(newSchedules.getSchedules())
                .isNotEmpty();
        assertThat(schedules.getSchedules())
                .isEmpty();
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

    public String visitFirstTalkOf(Schedule schedule) {
        MinimalTalkInfo firstTalk = schedule.getFirstTalk().getTalkInfo();
        return "You are now visiting your first talk for the day: \"" +
                firstTalk.getTitle() +
                "\" by " +
                firstTalk.getSpeakerNames().stream().collect(Collectors.joining(" and ")) +
                ".";
    }

    public String visitFirstTalkOf(BetterSchedule schedule) {
        MinimalTalkInfo firstTalk = schedule.getFirstTalk().getTalkInfo();
        return "You are now visiting your first talk for the day: \"" +
                firstTalk.getTitle() +
                "\" by " +
                firstTalk.getSpeakerNames().stream().collect(Collectors.joining(" and ")) +
                ".";
    }

    public String visitFirstTalkOf(BestSchedule schedule) {
        MinimalTalkInfo firstTalk = schedule.getFirstTalk().getTalkInfo();
        return "You are now visiting your first talk for the day: \"" +
                firstTalk.getTitle() +
                "\" by " +
                firstTalk.getSpeakerNames().stream().collect(Collectors.joining(" and ")) +
                ".";
    }
}
