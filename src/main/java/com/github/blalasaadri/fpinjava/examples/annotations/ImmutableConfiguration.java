package com.github.blalasaadri.fpinjava.examples.annotations;

import org.immutables.value.Value.Style;
import org.immutables.vavr.encodings.VavrOptionEncodingEnabled;

@Style(
        get = { "get*", "is*" },
        init = "with*"
)
@VavrOptionEncodingEnabled
public @interface ImmutableConfiguration {
}
