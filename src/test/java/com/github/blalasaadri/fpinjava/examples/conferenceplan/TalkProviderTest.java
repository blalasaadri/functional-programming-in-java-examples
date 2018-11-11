package com.github.blalasaadri.fpinjava.examples.conferenceplan;

import com.github.blalasaadri.fpinjava.examples.conferenceplan.TalkProvider;
import com.github.blalasaadri.fpinjava.examples.conferenceplan.model.ConferenceDay;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TalkProviderTest {
    @Test
    void readConferenceDays_providesNonEmptyAnswer() {
        TalkProvider talkProvider = new TalkProvider();

        List<ConferenceDay> conferenceDays = talkProvider.readConferenceDays();

        assertThat(conferenceDays)
                .isNotEmpty();
    }
}