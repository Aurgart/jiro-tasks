package java_jabi.jiro_tasks.model.reports;
import java.util.List;

public record ReportInfo(
        Long taskCount,
        List<TaskByStatusCnt> taskState,
        List<AssigneeTasksCount> assigneeTaskCount,
        Double workDays
) {
}
