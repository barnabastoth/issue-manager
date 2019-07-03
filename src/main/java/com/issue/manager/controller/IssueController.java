package com.issue.manager.controller;

import com.issue.manager.dto.NewIssue;
import com.issue.manager.entity.Issue;
import com.issue.manager.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/")
    private Iterable<Issue> serveAllIssue() {
        return issueService.getAllIssue();
    }

    @PutMapping("/")
    private Issue handleNewIssueCreation(@RequestBody NewIssue newIssue) {
        return issueService.createNewIssue(newIssue);
    }


}
