package java_jabi.jiro_tasks;

import java_jabi.jiro_tasks.model.Status;
import java_jabi.jiro_tasks.model.incoming.TaskData;
import java_jabi.jiro_tasks.repositaries.TaskRepository;
import java_jabi.jiro_tasks.service.ExternalUserService;
import java_jabi.jiro_tasks.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class JiroTasksTests {

	@Mock
	private TaskRepository taskLogic;
	@Mock
	private ExternalUserService userServ;
	@InjectMocks
	private TaskService taskService;

	@Test
	void createTaskTest() {
		RuntimeException excp = assertThrows(RuntimeException.class, () -> taskService.addTask(
				new TaskData("Test","Testovaya Rabota",Status.TO_DO,2L,2L,LocalDate.parse("2026-07-10"))));
		Assertions.assertNotNull(excp.getMessage());
	}

}
