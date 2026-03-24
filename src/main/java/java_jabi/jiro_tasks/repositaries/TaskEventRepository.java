package java_jabi.jiro_tasks.repositaries;

import java_jabi.jiro_tasks.model.TaskEvent;
import java_jabi.jiro_tasks.repositaries.Mapper.TaskEventMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskEventRepository {
    private static final String INSERT = """
            INSERT INTO jiro_task.task_event(task_id,title,description,state,author,assignee,dead_line,create_date,update_date,event_type)
            VALUES (:task_id,:title,:description,CAST(:state AS jiro_task.status),:author,:assignee,:dead_line,:create_date,:update_date,:event_type)
            RETURNING *;
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM jiro_task.task_event
            WHERE task_id = :task_id
            """;

    private final TaskEventMapper taskEventMapp;
    private final NamedParameterJdbcTemplate jbcTemplate;

    public TaskEvent insert(TaskEvent task) {
        return jbcTemplate.queryForObject(INSERT, taskParamForSql(task), taskEventMapp);
    }
    public List<TaskEvent> findTaskEvent(Long taskID) {
        return jbcTemplate.query(GET_BY_ID,new MapSqlParameterSource("task_id", taskID), taskEventMapp);
    }

    public MapSqlParameterSource taskParamForSql(TaskEvent task) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("task_id", task.getTaskId());
        params.addValue("title", task.getTitle());
        params.addValue("description", task.getDescription());
        params.addValue("state", task.getState().toString());
        params.addValue("author", task.getAuthorId());
        params.addValue("assignee", task.getAssignee());
        params.addValue("dead_line", task.getDeadLine());
        params.addValue("create_date", task.getCreateDate());
        params.addValue("update_date", task.getUpdateDate());
        params.addValue("event_type", task.getEventType());

        return params;
    }
}
