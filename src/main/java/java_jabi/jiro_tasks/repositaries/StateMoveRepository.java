package java_jabi.jiro_tasks.repositaries;


import java_jabi.jiro_tasks.model.StateMove;
import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.repositaries.Mapper.StateMoveMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class StateMoveRepository {
    private static final String GET_MOVE = """
            SELECT *
            FROM jiro_task.state_scheme
            WHERE state_from = :state_from::jiro_task.status
            AND state_to = :state_to::jiro_task.status
            """;

    private final StateMoveMapper stateMoveMapper;
    private final NamedParameterJdbcTemplate jbcTemplate;


    public StateMove findMove(Status stateFrom, Status stateTo) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("state_from", stateFrom.toString());
        params.addValue("state_to", stateTo.toString());
        return jbcTemplate.queryForObject(GET_MOVE, params, stateMoveMapper);
    }
}
