package com.issue.manager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.issue.manager.model.Priority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Issue")
public class Issue {

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "ID")
    private Long id;

    private String name;

    private String description;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    private Priority priority;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime createdAt;

    public Issue() {}

    public Issue(String name, String description, LocalDateTime dueDate, Priority priority, LocalDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.createdAt = createdAt;
    }
}
