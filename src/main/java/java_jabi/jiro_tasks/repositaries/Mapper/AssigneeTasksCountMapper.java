package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.reports.AssigneeTasksCount;
import java_jabi.jiro_tasks.model.reports.TaskByStatusCnt;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AssigneeTasksCountMapper implements RowMapper<AssigneeTasksCount> {
    @Override
    public AssigneeTasksCount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AssigneeTasksCount(
                rs.getLong("assignee"),
                rs.getLong("task_count"))
                ;
    }

}
