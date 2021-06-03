package com.example.epamtest.controller;

import com.example.epamtest.dto.ProjectDto;
import com.example.epamtest.dto.SubtaskDto;
import com.example.epamtest.dto.TaskDto;
import com.example.epamtest.dto.UserDto;
import com.example.epamtest.exception.ValidationException;
import com.example.epamtest.service.ProjectService;
import com.example.epamtest.service.SubtaskService;
import com.example.epamtest.service.TaskService;
import com.example.epamtest.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/test")
public class TestController {
    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final SubtaskService subtaskService;

    public TestController(UserService userService, ProjectService projectService, TaskService taskService,
                          SubtaskService subtaskService) {
        this.userService = userService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.subtaskService = subtaskService;
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String feedback(HttpServletRequest request, HttpServletResponse response){
        return "Object is null";
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public String handleInvalidTokenException(ValidationException e){
        return e.getMessage();
    }

    @PostMapping("/save_user")
    public UserDto saveUser(@RequestBody UserDto userDto, ValidationException e) throws ValidationException {
        UserDto a = userService.saveUser(userDto);
        return a;
    }

    @PostMapping("/save_task")
    public TaskDto saveTask(@RequestBody TaskDto taskDto) {
        return taskService.saveTask(taskDto);
    }

    @PostMapping("/save_project")
    public ProjectDto saveProject(@RequestBody ProjectDto projectDto) {
        return projectService.saveProject(projectDto);
    }


    @PostMapping("/save_subtask")
    public SubtaskDto saveSubtask(@RequestBody SubtaskDto subtaskDto) {
        return subtaskService.save(subtaskDto);
    }

    @GetMapping("/show_users")
    public List<UserDto> showUsers() {
        return userService.findAll();
    }

    @GetMapping("/show_project")
    public List<ProjectDto> showProject() {
        return projectService.findAll();
    }

    @GetMapping("/show_tasks")
    public List<TaskDto> showTask() {
        return taskService.findAll();
    }

    @GetMapping("/show_subtask")
    public List<SubtaskDto> showSubtask() {
        return subtaskService.findAll();
    }

    @GetMapping("/edit_project")
    public UserDto editProject(@RequestParam Integer idProject, @RequestParam Integer idUser) {
        return userService.assignUserOnProject(idProject, idUser);
    }

    @GetMapping("/edit_task")
    public TaskDto assignTaskOnUser(@RequestParam Integer taskId, @RequestParam Integer userId) {
        return taskService.assignTaskOnUser(taskId, userId);
    }

    @GetMapping("/get_task_user")
    public Set<TaskDto> getAllTaskUser(@RequestParam Integer idUser) {
        return userService.getAllTaskUser(idUser);
    }

    @GetMapping("/get_user_of_project")
    public Set<UserDto> getAllUserOfProject(@RequestParam Integer idProject) {
        return projectService.getAllUserOfProject(idProject);
    }

    @GetMapping("/get_report_task")
    public List<TaskDto> getTaskFromProjectAndUser(@RequestParam Integer idProject, @RequestParam Integer idUser) {
        return taskService.getTaskOfProjectOfUser(idProject, idUser);
    }

    @DeleteMapping("/delete_user")
    public void deleteUser(@RequestParam Integer idUser) {
        userService.deleteUser(idUser);
    }

    @GetMapping("/get_time_of_task")
    public String getTimeOfTask(@RequestParam Integer idTask) {
        Time time = taskService.getTimeOfSubtasks(idTask);
        String result = "Task left is: " + time;
        return result;
    }

    @GetMapping("/assign_subtask_on_task")
    public TaskDto assignSubtaskOnTask(@RequestParam Integer idTask, @RequestParam Integer idSubtask) {
        return taskService.assignSubtask(idTask, idSubtask);
    }
}
