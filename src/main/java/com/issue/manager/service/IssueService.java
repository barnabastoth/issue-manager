package com.issue.manager.service;

import com.issue.manager.dto.NewIssue;
import com.issue.manager.entity.Issue;
import com.issue.manager.model.Priority;
import com.issue.manager.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class IssueService {

    private final int LOW_PRIORITY_IN_MINUTES = 32 * 60;
    private final int MEDIUM_PRIORITY_IN_MINUTES = 16 * 60;
    private final int HIGH_PRIORITY_IN_MINUTES = 8 * 60;

    private final int WORK_DAY_IN_MINUTES = 8 * 60;

    @Autowired
    private IssueRepository issueRepository;

    public Iterable<Issue> getAllIssue() {
        return issueRepository.findAllByOrderByIdDesc();
    }

    public Issue createNewIssue(NewIssue newIssue) {
        LocalDateTime createdAt = LocalDateTime.now();
        Issue issue = new Issue(
                newIssue.getName(),
                newIssue.getDescription(),
                calculateDueDate(newIssue.getPriority(), createdAt),
                newIssue.getPriority(),
                createdAt
        );
        return issueRepository.save(issue);
    }

    private LocalDateTime calculateDueDate(Priority priority, LocalDateTime createdAt) {
        LocalDateTime dueDate = createdAt;

        int remainingWorkingMinutesUntilDueDate = calculateRemainingWorkingMinutesTilDueDate(priority);

        int remainingWorkingMinutesOfTheDay = calculateRemainingWorkingMinutesOfTheDay(createdAt);

        dueDate = dueDate.plusMinutes(remainingWorkingMinutesOfTheDay);
        remainingWorkingMinutesUntilDueDate -= remainingWorkingMinutesOfTheDay;

        while (remainingWorkingMinutesUntilDueDate > 0) {
            dueDate = addDayToDueDate(dueDate);
            if (remainingWorkingMinutesUntilDueDate > WORK_DAY_IN_MINUTES) {
                remainingWorkingMinutesUntilDueDate -= WORK_DAY_IN_MINUTES;
            } else if (remainingWorkingMinutesUntilDueDate == WORK_DAY_IN_MINUTES) {
                remainingWorkingMinutesUntilDueDate-= WORK_DAY_IN_MINUTES;
                dueDate = dueDate.plusMinutes(WORK_DAY_IN_MINUTES);
            } else {
                dueDate = dueDate.plusMinutes(remainingWorkingMinutesUntilDueDate);
                remainingWorkingMinutesUntilDueDate = 0;
            }
        }

        return dueDate;
    }

    private LocalDateTime addDayToDueDate(LocalDateTime dueDate) {
        if (dueDate.getDayOfWeek().toString().equals("FRIDAY")) {
            return dueDate.plusDays(3).withHour(9).withMinute(0);
        }
        return dueDate.plusDays(1).withHour(9).withMinute(0);
    }

    private int calculateRemainingWorkingMinutesOfTheDay(LocalDateTime currentTime) {
        return (int) currentTime.until(LocalDateTime.now().withHour(17).withMinute(0), ChronoUnit.MINUTES);
    }

    private int calculateRemainingWorkingMinutesTilDueDate(Priority priority) {
        if (priority == Priority.LOW) {
            return LOW_PRIORITY_IN_MINUTES;
        } else if (priority == Priority.MEDIUM) {
            return MEDIUM_PRIORITY_IN_MINUTES;
        } else if (priority == Priority.HIGH) {
            return HIGH_PRIORITY_IN_MINUTES;
        }
        return 0;
    }
}
