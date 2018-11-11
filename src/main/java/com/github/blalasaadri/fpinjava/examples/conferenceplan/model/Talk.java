package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import io.vavr.collection.List;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import java.util.Locale;

@Immutable
@ImmutableConfiguration
@JsonDeserialize(builder = ImmutableTalk.Builder.class)
public interface Talk {

    String getTrackId();

    TalkType getTalkType();

    String getTrack();

    String getSummaryAsHtml();

    String getId();

    java.util.List<Speaker> getSpeakers();

    @Derived
    default List<Speaker> getSpeakersVavr() {
        return List.ofAll(getSpeakers());
    }

    String getTitle();

    @JsonProperty("lang")
    Locale getLanguage();

    String getSummary();

    static ImmutableTalk.Builder builder() {
        return ImmutableTalk.builder();
    }
}
