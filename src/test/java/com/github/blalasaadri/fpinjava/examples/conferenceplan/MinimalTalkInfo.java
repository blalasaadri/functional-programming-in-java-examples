package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.RatedTalk;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.Speaker;
import org.immutables.value.Value;
import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Parameter;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Immutable
@ImmutableConfiguration
public interface MinimalTalkInfo {

    @Parameter
    String getTitle();

    @Value.Redacted
    @Parameter
    List<String> getSpeakerNames();

    @Value.Redacted
    @Parameter
    LocalTime getStartTime();

    @Value.Redacted
    @Parameter
    LocalTime getEndTime();

    @Value.Redacted
    @Parameter
    int getRating();

    static MinimalTalkInfo of(String title, Iterable<String> speakerNames, LocalTime startTime, LocalTime endTime, int rating) {
        return ImmutableMinimalTalkInfo.of(title, speakerNames, startTime, endTime, rating);
    }

    static ImmutableMinimalTalkInfo.Builder builder() {
        return ImmutableMinimalTalkInfo.builder();
    }

    static Optional<MinimalTalkInfo> fromRatedTalk(RatedTalk ratedTalk) {
        return ratedTalk.getSlot()
                .getTalk()
                .map(talk -> builder()
                        .withTitle(talk.getTitle())
                        .withSpeakerNames(talk.getSpeakers().stream().map(Speaker::getName).collect(Collectors.toList()))
                        .withStartTime(ratedTalk.getSlot().getFromTime())
                        .withEndTime(ratedTalk.getSlot().getToTime())
                        .withRating(ratedTalk.getRating())
                        .build()
                );
    }
}
