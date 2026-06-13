package com.appbit.matching.ai;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "flowise")
public class FlowiseProperties {
    private String url;
    private String chatflowId;
    private String apiKey;
}