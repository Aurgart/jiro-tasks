package java_jabi.jiro_tasks.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.Task;
import java_jabi.jiro_tasks.model.TaskUpdate;
import java_jabi.jiro_tasks.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tasks")
@Tag(name = "Задачи")
public class TaskController {
    private final TaskService tasks;

    @PostMapping
    @Operation(summary = "Создать задачу")
    public Task create(@RequestBody Task task){
        return tasks.addTask(task);
    }
    @GetMapping("/task/{id}")
    @Operation(summary = "Получать таску по ид")
    public Task getByID(@PathVariable("id") Long id){
        return tasks.getTask(id);
    }
    @PatchMapping
    @Operation(summary = "Обновить данные")
    public Task updateTask(@RequestBody TaskUpdate task){
        return tasks.updateTask(task);
    }
    @GetMapping("/task_list")
    @Operation(summary = "Получить список тасок по параметрам.")
    public List<Task> getTaskList(@RequestParam(required = true) Long assigneeID, @RequestParam(required = true) Status state){
        return tasks.getTaskList(state,assigneeID);
    }

}
