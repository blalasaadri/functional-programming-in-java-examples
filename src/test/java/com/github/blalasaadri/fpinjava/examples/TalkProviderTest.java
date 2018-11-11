package com.github.blalasaadri.fpinjava.examples;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TalkProviderTest {
    @Test
    void hw() {
        assertThat(new TalkProvider().hw())
                .isEqualTo("Hello, World!");
    }
}