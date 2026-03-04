package java_jabi.jiro_tasks.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class TaskUpdate {
    private Long id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private Status state;
    private Long assignee;
}
