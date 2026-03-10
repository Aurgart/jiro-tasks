package java_jabi.jiro_tasks.repositaries;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.Task;
import java_jabi.jiro_tasks.model.TaskData;
import java_jabi.jiro_tasks.model.TaskUpdate;
import java_jabi.jiro_tasks.repositaries.Mapper.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskRepository {
    private static final String INSERT = """
            INSERT INTO jiro_task.task(title,description,state,author,assignee,dead_line,create_date)
            VALUES (:title,:description,CAST(:state AS jiro_task.status),:author,:assignee,:dead_line,now())
            RETURNING *;
            """;
    private static final String UPDATE = """
            UPDATE jiro_task.task
            SET  description = :description,
                state = :state,
                assignee = :assignee,
                dead_line = :dead_line,
                update_date = now()
            WHERE id = :id
            RETURNING *;
            """;
    private static final String DELETE = """
            UPDATE jiro_task.task
            SET  state = 'REJECTED'
            WHERE id = :id
            RETURNING *;
            """;
    private static final String GET_BY_ID = """
            SELECT *
            FROM jiro_task.task
            WHERE id = :id
            """;
    private static final String GET_TASK = """
            SELECT *
            FROM jiro_task.task t
            WHERE (t.assignee = :assignee or :assignee is null)
            AND (t.state = :state::jiro_task.status or :state::jiro_task.status is null)
            """;

    private final TaskMapper taskMapp;
    private final NamedParameterJdbcTemplate jbcTemplate;

    public Task insert(TaskData task) {
        return jbcTemplate.queryForObject(INSERT, taskParamForSql(task), taskMapp);
    }

    public Task update(TaskUpdate task) {
        return jbcTemplate.queryForObject(UPDATE, taskUpdParamForSql(task), taskMapp);
    }
    public void delete(Long id) {
        jbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
    }

    public Task getById(Long id) {
        return jbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), taskMapp);
    }

    public List<Task> findTask(Status state, Long assigneeId) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("state", state == null? null : state.toString());
        params.addValue("assignee", assigneeId);
        return jbcTemplate.query(GET_TASK,params, taskMapp);
    }

    public MapSqlParameterSource taskParamForSql(TaskData task) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("title", task.title());
        params.addValue("description", task.description());
        params.addValue("state", task.state().toString());
        params.addValue("author", task.authorId());
        params.addValue("assignee", task.assignee());
        params.addValue("dead_line", task.deadLine());

        return params;
    }

    public MapSqlParameterSource taskUpdParamForSql(TaskUpdate task) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", task.getId());
        params.addValue("title", task.getTitle());
        params.addValue("description", task.getDescription());
        params.addValue("state", task.getState());
        params.addValue("assignee", task.getAssignee());
        params.addValue("dead_line", task.getDeadLine());

        return params;
    }

}
