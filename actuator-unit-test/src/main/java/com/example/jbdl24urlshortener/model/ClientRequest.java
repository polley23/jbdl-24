package com.example.jbdl24urlshortener.model;
import lombok.*;

import javax.persistence.GeneratedValue;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequest {
    private String name;
    private String host;
    private String port;
    private Long expireAfterMillis;
}
