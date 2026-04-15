package java_jabi.jiro_tasks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class TaskEvent {
    private Long id;
    private Long taskId;
    private String title;
    private String description;
    private Status state;
    private Long authorId;
    private Long assignee;
    private LocalDate deadLine;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String eventType;

    public TaskEvent(Task task) {
        this.taskId = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.assignee = task.getAssignee();
        this.authorId = task.getAuthorId();
        this.createDate = task.getCreateDate();
        this.state = task.getState();
        this.deadLine = task.getDeadLine();
        this.eventType = "C";
    }

    public TaskEvent(TaskUpdate task) {
        this.taskId = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.deadLine = task.getDeadLine();
        this.state = task.getState();
        this.assignee = task.getAssignee();
        this.eventType = "U";
    }

    public TaskEvent(Long taskID) {
        this.taskId = taskID;
        this.eventType = "D";
    }
}