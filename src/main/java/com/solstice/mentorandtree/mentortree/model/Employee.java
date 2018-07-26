package com.solstice.mentorandtree.mentortree.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;


public class Employee {


    private Long id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String imageUrl;
    @Getter
    @Setter
    private String url;

    @JsonProperty("_links")
    private void unpackNested(Map<String, Object> links) {
        Map<String, String> self = (Map<String, String>)links.get("self");
        url = self.get("href");
    }


    public void setEmployeeId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

}
