package java_jabi.jiro_tasks.service;


import java_jabi.jiro_tasks.exception.StateMoveException;
import java_jabi.jiro_tasks.model.StateMove;
import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.repositaries.StateMoveRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StateMoveService {
    private final StateMoveRepository stateScheme;

    public void chkMove(Status stateFrom, Status stateTo) {
        StateMove tmp = stateScheme.findMove(stateFrom, stateTo);
        if (tmp == null) {
            throw new StateMoveException("Такого перехода не существует.");
        }
        if (tmp.isForbidden()) {
            throw new StateMoveException("Такой переход запрещен.");
        }
    }
}
