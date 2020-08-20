package es.bit.BananaTaskManager.api;

import es.bit.BananaTaskManager.models.ErrorMessage;
import es.bit.BananaTaskManager.models.Project;
import es.bit.BananaTaskManager.models.Task;
import es.bit.BananaTaskManager.models.User;
import es.bit.BananaTaskManager.persistence.InMemoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class APIController {
    private final InMemoryRepository repo=new InMemoryRepository();

    private final User user=new User(repo);

    private final Project project=new Project(repo);

    private final Task task=new Task(repo);

    /* Users block */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Collection<User> getAllUsers() {
        return user.findAllUsers();
    }

    @RequestMapping(value = "/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity getOneUser(@PathVariable Long userid) {
        User possibleUser = user.findUserById(userid);
        if (possibleUser!=null) {
            return new ResponseEntity(possibleUser, HttpStatus.OK);
        }
        return new ResponseEntity(
                ErrorMessage.builder().message("User with id " + userid + " was not found").build(),
                HttpStatus.NOT_FOUND
        );
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity postUser(@Valid @RequestBody User newUser) {
        User newuser = user.addUser(newUser);
        if (newuser.getUid() > 0) {
            return new ResponseEntity("{\"id\": " + newuser.getUid() + "}", HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    /* Projects block */
    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public Collection<Project> getAllProjects() {
        return project.findAllProjects();
    }

    @RequestMapping(value = "/projects/{projectid}", method = RequestMethod.GET)
    public ResponseEntity getOneProject(@PathVariable Long projectid) {
        Project possibleProject = project.findProjectById(projectid);
        if (possibleProject!=null) {
            return new ResponseEntity(possibleProject, HttpStatus.OK);
        }
        return new ResponseEntity(
                ErrorMessage.builder().message("Project with id " + projectid + " was not found").build(),
                HttpStatus.NOT_FOUND
        );
    }

    @RequestMapping(value = "/projects", method = RequestMethod.POST)
    public ResponseEntity postProject(@Valid @RequestBody Project newProject) {
        Project newproject = project.addProject(newProject);
        if (newproject.getPid() > 0) {
            return new ResponseEntity("{\"id\": " + newproject.getPid() + "}", HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    /* Tasks block */
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public Collection<Task> getAllTasks() {
        return task.findAllTasks();
    }

    @RequestMapping(value = "/tasks/{taskid}", method = RequestMethod.GET)
    public ResponseEntity getOneTask(@PathVariable Long taskid) {
        Task possibleTask = task.findTaskById(taskid);
        if (possibleTask!=null) {
            return new ResponseEntity(possibleTask, HttpStatus.OK);
        }
        return new ResponseEntity(
                ErrorMessage.builder().message("Task with id " + taskid + " was not found").build(),
                HttpStatus.NOT_FOUND
        );
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity postTask(@Valid @RequestBody Task newTask) {
        Task newtask = task.addTask(newTask);
        if (newtask.getTid() > 0) {
            return new ResponseEntity("{\"id\": " + newtask.getTid() + "}", HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
