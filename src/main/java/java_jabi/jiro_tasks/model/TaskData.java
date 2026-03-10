package java_jabi.jiro_tasks.model;

import java.time.LocalDate;

public record TaskData( String title,
         String description,
         Status state,
         Long authorId,
         Long assignee,
         LocalDate deadLine
        ) {
}
