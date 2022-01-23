package com.gfg.jbdl24kafkaconsumer;
import lombok.*;



@Getter
@Setter
public class TopicConfiguration {
    final String name;

    public TopicConfiguration(final String name, final Integer partitonOffset) {
        this.name = name;
        this.partitonOffset = partitonOffset;
    }

    public String getName() {
        return name;
    }

    public Integer getPartitonOffset() {
        return partitonOffset;
    }

    final Integer partitonOffset;
}
