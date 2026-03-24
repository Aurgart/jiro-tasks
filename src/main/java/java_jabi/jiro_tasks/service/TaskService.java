package java_jabi.jiro_tasks.service;

import java_jabi.jiro_tasks.exception.TaskException;
import java_jabi.jiro_tasks.model.*;
import java_jabi.jiro_tasks.repositaries.TaskEventRepository;
import java_jabi.jiro_tasks.repositaries.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository tasks;
    private final TaskEventRepository tasksEvents;
    private final ExternalUserService users;
    private final StateMoveService stateScheme;

    public Task addTask(TaskData task){
        validateTask(task);
        Task tmp =  tasks.insert(task);
        addEvent(tmp);
        return tmp;
    }

    public Task updateTask(TaskUpdate task){
        validateTaskUpdate(task);
        Task tmp = tasks.update(task);
        addEvent(task);
        return tmp;
    }

    public Task getTask(Long taskID){
        return tasks.getById(taskID);
    }

    public void deleteTask(Long taskID){
        tasks.delete(taskID);
        addEvent(taskID);
    }


    public List<Task> getTaskList(Status state, Long assigneeID){
        checkUser(assigneeID);
        return tasks.findTask(state, assigneeID);
    }


    public List<TaskEvent> getTaskEventList(Long taskId){
        return tasksEvents.findTaskEvent(taskId);
    }


    private void validateTask(TaskData task){
        if(task.title().isBlank()){
            throw new TaskException("Титул не указан!");
        }
        if(task.description().isBlank()){
            throw new TaskException("Описание пустое!");
        }
        if(task.deadLine() == null || task.deadLine().isBefore(LocalDate.now())){
            throw new TaskException("Дедлайн не может быть не указан, или указан раньше чем сегодня!");
        }
        validateUser(task.authorId());
        validateUser(task.assignee());
    }
    private void validateUser(Long id){
        if(!users.checkUser(id)){
            throw new TaskException("Пользователь должен работать!");
        }
    }
    private void validateTaskUpdate(TaskUpdate task){
        if(task.getTitle().isBlank()){
            throw new TaskException("Титул не указан!");
        }
        if(task.getDescription().isBlank()){
            throw new TaskException("Описание пустое!");
        }
        if(task.getDeadLine() == null || task.getDeadLine().isBefore(LocalDate.now())){
            throw new TaskException("Дедлайн не может быть не указан, или указан раньше чем сегодня!");
        }
        checkStateMove(task);
        checkUser(task.getAssignee());
    }
    /*на случай обновления поля на уже существующего.*/
    private void checkUser(Long id){
        if(!users.checkHistUser(id)){
            throw new TaskException("Пользователь должен был когда-то работать!");
        }
    }
    private void checkStateMove(TaskUpdate task){
        Status state_from = tasks.getById(task.getId()).getState();
        stateScheme.chkMove(state_from,task.getState());
    }

    private void checkManagerRole(TaskUpdate task){
        if(task.getAssignee() != tasks.getById(task.getId()).getAssignee()) {
            if (!users.checkManagerRole(task.getUserId())) {
                throw new TaskException("Пользователь должен быть менеджером!");
            }
        }
    }

    private void addEvent(Task task){
        TaskEvent tmp = new TaskEvent(task);
        tasksEvents.insert(tmp);
    }
    private void addEvent(TaskUpdate task){
        TaskEvent tmp = new TaskEvent(task);
        tasksEvents.insert(tmp);
    }
    private void addEvent(Long id){
        TaskEvent tmp = new TaskEvent(id);
        tasksEvents.insert(tmp);
    }
}
