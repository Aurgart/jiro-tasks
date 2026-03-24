package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.TaskEvent;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskEventMapper  implements RowMapper<TaskEvent> {
    @Override
    public TaskEvent mapRow(ResultSet rs, int rownum) throws SQLException {
        return TaskEvent.builder()
                .id(rs.getLong("id"))
                .taskId(rs.getLong("task_id"))
                .state(Enum.valueOf(Status.class, rs.getString("state")) )
                .title(rs.getString("title"))
                .assignee(rs.getLong("assignee"))
                .createDate(rs.getDate("create_date")== null ? null : rs.getDate("create_date").toLocalDate())
                .deadLine(rs.getDate("dead_line")== null ? null : rs.getDate("dead_line").toLocalDate())
                .authorId(rs.getLong("author"))
                .description(rs.getString("description"))
                .updateDate(rs.getDate("update_date")== null ? null : rs.getDate("update_date").toLocalDate())
                .eventType(rs.getString("event_type"))
                .build();
    }
}
