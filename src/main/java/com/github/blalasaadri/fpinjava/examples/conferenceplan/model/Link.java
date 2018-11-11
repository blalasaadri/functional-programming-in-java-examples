package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.github.blalasaadri.fpinjava.examples.annotations.ImmutableConfiguration;
import org.immutables.value.Value.Immutable;

import java.net.URI;

@Immutable
@ImmutableConfiguration
@JsonDeserialize(builder = ImmutableLink.Builder.class)
public interface Link {
    URI getHref();

    URI getRel();

    String getTitle();

    String getUuid();

    static ImmutableLink.Builder builder() {
        return ImmutableLink.builder();
    }
}
