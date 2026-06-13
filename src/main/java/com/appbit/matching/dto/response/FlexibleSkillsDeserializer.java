package com.appbit.matching.dto.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FlexibleSkillsDeserializer extends StdDeserializer<List<String>> {

    public FlexibleSkillsDeserializer() {
        super(List.class);
    }

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == JsonToken.START_ARRAY) {
            return ctxt.readValue(p, List.class);
        } else {
            return Arrays.asList(p.getText().split(",\\s*"));
        }
    }
}