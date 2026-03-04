package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.Task;
import org.springframework.stereotype.Component;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TaskMapper implements RowMapper<Task> {

    @Override
    public Task mapRow(ResultSet rs, int rownum) throws SQLException{
        return Task.builder()
                .id(rs.getLong("id"))
                .state(Enum.valueOf(Status.class, rs.getString("state")) )
                .title(rs.getString("title"))
                .assignee(rs.getLong("assignee"))
                .createDate(rs.getDate("create_date").toLocalDate())
                .deadLine(rs.getDate("dead_line").toLocalDate())
                .authorId(rs.getLong("author"))
                .description(rs.getString("description"))
                .updateDate(rs.getDate("update_date").toLocalDate())
                .build();
    }
}
