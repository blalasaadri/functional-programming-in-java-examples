package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import org.immutables.value.Value.Immutable;

@Immutable
@ImmutableConfiguration
@JsonDeserialize(builder = ImmutableSpeaker.Builder.class)
public interface Speaker {

    String getName();

    Link getLink();

    static ImmutableSpeaker.Builder builder() {
        return ImmutableSpeaker.builder();
    }
}
