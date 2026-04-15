package java_jabi.jiro_tasks.repositaries;


import java_jabi.jiro_tasks.model.reports.AssigneeTasksCount;
import java_jabi.jiro_tasks.model.reports.ReportInfo;
import java_jabi.jiro_tasks.model.reports.TaskByStatusCnt;
import java_jabi.jiro_tasks.repositaries.Mapper.*;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class ReportRepository {

    private static final String GET_ALL_INFO = """
            SELECT count(*) task_count,ROUND(AVG(t.dead_line - t.create_date)::numeric, 2) work_days
            FROM jiro_task.task t
            WHERE t.create_date  between :date_from and :date_to
            and t.assignee in (:ids)
            """;
    private static final String GET_TASK_STATE_INFO = """
            select count(*) state_count,t.state
            from jiro_task.task t
            where t.create_date between :date_from and :date_to
            and t.assignee in (:ids)
            group by t.state
            """;
    private static final String GET_USER_INFO = """
            select count(*) task_count,t.assignee
            from jiro_task.task t
            where t.create_date between :date_from and :date_to
            and t.assignee in (:ids)
            group by t.assignee
            """;

    private final AssigneeTasksCountMapper assMapp;
    private final TaskByStatusCntMapper taskStateMapp;
    private final ReportInfoMapper reportMapp;
    private final NamedParameterJdbcTemplate jbcTemplate;

    public ReportInfo getReportInfo(LocalDate dateFrom, LocalDate dateTo, List<Long> userIds) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date_from", dateFrom);
        params.addValue("date_to", dateTo);
        params.addValue("ids", userIds);
        return jbcTemplate.queryForObject(GET_ALL_INFO, params, reportMapp);
    }
    public List<TaskByStatusCnt> getReportStateInfo(LocalDate dateFrom, LocalDate dateTo, List<Long> userIds) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date_from", dateFrom);
        params.addValue("date_to", dateTo);
        params.addValue("ids", userIds);
        return jbcTemplate.query(GET_TASK_STATE_INFO, params, taskStateMapp);
    }
    public List<AssigneeTasksCount> getReportAssInfo(LocalDate dateFrom, LocalDate dateTo, List<Long> userIds) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date_from", dateFrom);
        params.addValue("date_to", dateTo);
        params.addValue("ids", userIds);
        return jbcTemplate.query(GET_USER_INFO, params, assMapp);
    }
}
