package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.Task;
import java_jabi.jiro_tasks.model.TaskListResp;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskListRespMapper   implements RowMapper<TaskListResp> {
    @Override
    public TaskListResp mapRow(ResultSet rs, int rownum) throws SQLException {
        return TaskListResp.builder()
                .title(rs.getString("title"))
                .deadLine(rs.getDate("dead_line").toLocalDate())
                .description(rs.getString("description"))
                .build();
    }
}
