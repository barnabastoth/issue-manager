package com.issue.manager;

import com.issue.manager.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitialLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IssueService issueService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }
}
