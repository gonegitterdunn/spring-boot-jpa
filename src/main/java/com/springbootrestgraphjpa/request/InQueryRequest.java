package com.springbootrestgraphjpa.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class InQueryRequest {
    private List<String> firstNames;
}
