package java_jabi.jiro_tasks.service;

import java_jabi.jiro_tasks.exception.TaskException;
import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.Task;
import java_jabi.jiro_tasks.model.TaskData;
import java_jabi.jiro_tasks.model.TaskUpdate;
import java_jabi.jiro_tasks.repositaries.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository tasks;
    private final ExternalUserService users;

    public Task addTask(TaskData task){
        validateTask(task);
        return tasks.insert(task);
    }

    public Task updateTask(TaskUpdate task){
        validateTaskUpdate(task);
        return tasks.update(task);
    }

    public Task getTask(Long taskID){
        return tasks.getById(taskID);
    }

    public List<Task> getTaskList(Status state, Long assigneeID){
        checkUser(assigneeID);
        return tasks.findTask(state, assigneeID);
    }

    private void validateTask(TaskData task){
        if(task.title().isBlank()){
            throw new TaskException("Титут не указан!");
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
            throw new TaskException("Титут не указан!");
        }
        if(task.getDescription().isBlank()){
            throw new TaskException("Описание пустое!");
        }
        if(task.getDeadLine() == null || task.getDeadLine().isBefore(LocalDate.now())){
            throw new TaskException("Дедлайн не может быть не указан, или указан раньше чем сегодня!");
        }
        checkUser(task.getAssignee());
    }
    /*на случай обновления поля на уже существующего.*/
    private void checkUser(Long id){
        if(!users.checkHistUser(id)){
            throw new TaskException("Пользователь должен был когда-то работать!");
        }
    }
}
