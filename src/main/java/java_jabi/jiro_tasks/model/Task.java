package java_jabi.jiro_tasks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;

@Data
@AllArgsConstructor
@Builder
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status state;
    private Long authorId;
    private Long assignee;
    private LocalDate deadLine;
    private LocalDate createDate;
    private LocalDate updateDate;
}
