package com.github.blalasaadri.fpinjava.examples.conferenceplan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TalkType {
    @JsonProperty("Deep Dive")
    DEEP_DIVE,
    @JsonProperty("Hands-on Lab")
    HANDS_ON_LAB,
    @JsonProperty("Tools-in-Action")
    TOOLS_IN_ACTION,
    @JsonProperty("BOF")
    BOF,
    @JsonProperty("Keynote")
    KEYNOTE,
    @JsonProperty("Conference")
    CONFERENCE,
    @JsonProperty("Ignite")
    IGNITE,
    @JsonProperty("Quickie")
    QUICKIE
}
