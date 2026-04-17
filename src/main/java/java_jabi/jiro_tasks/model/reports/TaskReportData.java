package java_jabi.jiro_tasks.model.reports;

import java.time.LocalDate;
import java.util.List;

public record TaskReportData(List<Long> userIds, LocalDate dateFrom, LocalDate dateTo)  {
}
