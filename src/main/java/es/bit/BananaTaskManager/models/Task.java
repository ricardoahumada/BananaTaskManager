package es.bit.BananaTaskManager.models;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import es.bit.BananaTaskManager.persistence.InMemoryRepository;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	private long tid;

    @NonNull
    private String description;

    private Date initDate;
    
    private int duration;
    
    private User responsible;
    
    private int state;
    
    private Project project;

    private InMemoryRepository repo;

    public Task(InMemoryRepository repo){
        this.repo=repo;
    }

    public void initTask(){
        this.initDate=new Date();
        this.duration=0;
        this.state=1;
    }

    public void endTask(int duration){
        this.duration= duration;
        this.state=2;
    }

    public void changeState(int state){
        this.state=state;
    }

    public Task findTaskById(Long id) {
        return repo.findOneTask(id);
    }

    public Collection<Task> findAllTasks() {
        return repo.findAllTasks();
    }

    public Task addTask(Task task) {
        return repo.saveTask(task);
    }

    public void deleteTask(Long id) {
        Task task=repo.findOneTask(id);
        if(task!=null) repo.deleteTask(task);
    }

}
