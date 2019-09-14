package com.bomibear.taskmaster.Controller;

import com.bomibear.taskmaster.Model.History;
import com.bomibear.taskmaster.Model.S3Client;
import com.bomibear.taskmaster.Model.Task;
import com.bomibear.taskmaster.Model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    private S3Client s3Client;
    @Autowired
    TaskController(S3Client s3Client) {this.s3Client = s3Client;}

    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return (List) taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task addNewTask(@RequestBody Task task){
        Task newTask = new Task(task.getTitle(), task.getDescription());
        if(task.getAssignee() == null){
            History history = new History("Task was created and is " + newTask.getStatus());
            newTask.addToHistory(history);
        } else {
            newTask.setAssignee(task.getAssignee());
            newTask.setStatus("assigned");
            History history = new History("Task was created and assigned to " + task.getAssignee());
            newTask.addToHistory(history);
        }
        taskRepository.save(newTask);
        return newTask;
    }

    @GetMapping("/users/{name}/tasks")
    public List<Task> getUsersTasks(@PathVariable String name){
        return (List) taskRepository.findAllByAssignee(name);
    }

    @PutMapping("/tasks/{id}/state")
    public Task updateStatus(@PathVariable String id){
        Task task = taskRepository.findById(id).get();

        String status = task.getStatus();

        if(task.getAssignee() != null){
            switch(status){
                case "available":
                    task.setStatus("assigned");
                    History history = new History("Task was assigned to " + task.getAssignee());
                    task.addToHistory(history);
                    break;

                case "assigned":
                    task.setStatus("accepted");
                    History history2 = new History("Task was accepted by " + task.getAssignee());
                    task.addToHistory(history2);
                    break;

                case "accepted":
                    task.setStatus("finished");
                    History history3 = new History("Task was finished by " + task.getAssignee());
                    task.addToHistory(history3);
                    task.setStatus("finished");
            }
            taskRepository.save(task);
        }

        return task;
    }

    @PutMapping("/tasks/{id}/assign/{assignee}")
    public Task updateAssignee(@PathVariable String id, @PathVariable String assignee){
        Task task = taskRepository.findById(id).get();
        task.setAssignee(assignee);
        task.setStatus("assigned");

        History history = new History(task.getTitle() + " was assigned to " + assignee);

        task.addToHistory(history);
        taskRepository.save(task);
        return task;
    }

    @PostMapping("tasks/{id}/images")
    public Task addImage(
            @PathVariable String id,
            @RequestPart(value = "file") MultipartFile file){
        String pic = this.s3Client.uploadFile(file);
        Task task = taskRepository.findById(id).get();
        task.setPic(pic);
        taskRepository.save(task);

        return task;
    }

    @GetMapping("tasks/{id}")
    public Task getImage(@PathVariable String id){
        Task task = taskRepository.findById(id).get();
        return task;
    }

}

