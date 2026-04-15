package java_jabi.jiro_tasks.repositaries.Mapper;

import java_jabi.jiro_tasks.model.reports.ReportInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class ReportInfoMapper implements RowMapper<ReportInfo> {
    @Override
    public ReportInfo mapRow(ResultSet rs, int rownum) throws SQLException {
        return new ReportInfo(
                rs.getLong("task_count"),
                new ArrayList<>(),
                new ArrayList<>(),
                rs.getDouble("work_days"));
    }
}
