package java_jabi.jiro_tasks.model;

import lombok.Data;

@Data
public class ApiError {
    final boolean result;
    final String description;
}