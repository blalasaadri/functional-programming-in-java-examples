package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.ConferenceDay;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.ImmutableRatedTalk;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.RatedTalk;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.Slot;
import io.vavr.Value;
import io.vavr.collection.List;
import io.vavr.control.Try;

import java.time.DayOfWeek;
import java.util.Random;

public class TalkProvider {
    private static final long RATING_SEED = 4;

    private final ObjectMapper objectMapper;
    private final List<String> resourceNames = List.of("monday.json", "tuesday.json", "wednesday.json", "thursday.json", "friday.json");

    public TalkProvider() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);
    }

    public List<ConferenceDay> readConferenceDaysVavr() {
        return resourceNames
                .map(TalkProvider.class::getResource)
                .map(resource -> Try.of(() -> objectMapper.readValue(resource, ConferenceDay.class)))
                .map(result -> result.onFailure(failure -> {
                    throw new RuntimeException(failure);
                }))
                .flatMap(Value::toOption);
    }

    public java.util.List<ConferenceDay> readConferenceDays() {
        return readConferenceDaysVavr().toJavaList();
    }

    public java.util.List<RatedTalk> rateTalks(java.util.List<Slot> slots) {
        return rateTalks(RATING_SEED, slots);
    }

    public java.util.List<RatedTalk> rateTalks(long seed, java.util.List<Slot> slots) {
        Random random = new Random(seed);

        return List.ofAll(slots)
                .map(slot -> RatedTalk.builder().withSlot(slot))
                .map(builder -> builder.withRating(random.nextInt(5) + 1))
                .map(builder -> (RatedTalk) builder.build())
                .toJavaList();
    }

    public List<RatedTalk> getRatedTalksVavr(DayOfWeek day) {
        return getRatedTalksVavr(RATING_SEED, day);
    }

    public List<RatedTalk> getRatedTalksVavr(long seed, DayOfWeek day) {
        Random random = new Random(seed);

        return readConferenceDaysVavr()
                .filter(conferenceDay -> conferenceDay.getSlotsVavr().map(Slot::getDay).contains(day))
                .flatMap(ConferenceDay::getSlotsVavr)
                .map(slot -> RatedTalk.builder().withSlot(slot))
                .map(builder -> builder.withRating(random.nextInt(5) + 1))
                .map(ImmutableRatedTalk.Builder::build);
    }

    public java.util.List<RatedTalk> getRatedTalks(DayOfWeek day) {
        return getRatedTalksVavr(day).toJavaList();
    }
}
