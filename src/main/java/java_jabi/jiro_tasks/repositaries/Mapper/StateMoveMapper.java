package java_jabi.jiro_tasks.repositaries.Mapper;


import java_jabi.jiro_tasks.model.StateMove;
import java_jabi.jiro_tasks.model.Status;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StateMoveMapper  implements RowMapper<StateMove> {

    @Override
    public StateMove mapRow(ResultSet rs, int rownum) throws SQLException {
        return StateMove.builder()
                .id(rs.getLong("id"))
                .stateFrom(Enum.valueOf(Status.class, rs.getString("state_from")))
                .stateTo(Enum.valueOf(Status.class, rs.getString("state_to")))
                .forbidden(rs.getBoolean("forbidden"))
                .build();
    }
}
