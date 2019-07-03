package com.issue.manager.dto;

import com.issue.manager.model.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewIssue {
    private String name;
    private String description;
    private Priority priority;
}
