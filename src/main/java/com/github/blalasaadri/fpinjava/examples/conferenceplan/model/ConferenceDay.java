package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import io.vavr.collection.List;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

@Immutable
@ImmutableConfiguration
@JsonDeserialize(builder = ImmutableConferenceDay.Builder.class)
public interface ConferenceDay {
    java.util.List<Slot> getSlots();

    @Derived
    default List<Slot> getSlotsVavr() {
        return List.ofAll(getSlots());
    }

    ConferenceDay withSlots(Iterable<? extends Slot> slots);

    static ImmutableConferenceDay.Builder builder() {
        return ImmutableConferenceDay.builder();
    }
}
