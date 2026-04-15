package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.reports.TaskByStatusCnt;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskByStatusCntMapper implements RowMapper<TaskByStatusCnt>{
    @Override
    public TaskByStatusCnt mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TaskByStatusCnt(
                rs.getString("state"),
                rs.getLong("state_count"));
    }

}
