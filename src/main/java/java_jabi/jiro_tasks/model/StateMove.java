package java_jabi.jiro_tasks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class StateMove {
    private Long id;
    private Status stateFrom;
    private Status stateTo;
    private boolean forbidden;
}
