package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import io.vavr.control.Option;
import org.immutables.value.Value.Derived;
import org.immutables.value.Value.Immutable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

@Immutable
@ImmutableConfiguration
@JsonDeserialize(builder = ImmutableSlot.Builder.class)
public interface Slot {

    Room getRoomId();

    int getRoomCapacity();

    String getRoomName();

    boolean isNotAllocated();

    Optional<Object> getBreak();

    @Derived
    default Option<Object> getBreakVavr() {
        return Option.ofOptional(getBreak());
    }

    String getRoomSetup();

    Optional<Talk> getTalk();

    @Derived
    default Option<Talk> getTalkVavr() {
        return Option.ofOptional(getTalk());
    }

    long getFromTimeMillis();

    LocalTime getFromTime();

    long getToTimeMillis();

    LocalTime getToTime();

    String getSlotId();

    DayOfWeek getDay();

    static ImmutableSlot.Builder builder() {
        return ImmutableSlot.builder();
    }
}
