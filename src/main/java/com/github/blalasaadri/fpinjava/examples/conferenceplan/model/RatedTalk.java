package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import org.immutables.value.Value.Immutable;

@Immutable
@ImmutableConfiguration
public interface RatedTalk {

    Slot getSlot();

    int getRating();

    static ImmutableRatedTalk.Builder builder() {
        return ImmutableRatedTalk.builder();
    }
}
