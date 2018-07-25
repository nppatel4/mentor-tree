package com.solstice.mentorandtree.mentortree.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String url;

    @JsonProperty("_links")
    private void unpackNested(Map<String, Object> links) {
        Map<String, String> self = (Map<String, String>)links.get("self");
        url = self.get("href");
    }

}
