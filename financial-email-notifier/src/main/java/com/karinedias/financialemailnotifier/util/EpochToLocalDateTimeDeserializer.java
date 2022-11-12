package com.karinedias.financialemailnotifier.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class EpochToLocalDateTimeDeserializer extends StdDeserializer {

    private static final long serialVersionUID = 1L;

    public EpochToLocalDateTimeDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return Instant.ofEpochMilli(jsonParser.readValueAs(Long.class)).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
