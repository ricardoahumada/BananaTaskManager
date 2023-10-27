package com.banana.BananaTaskManager.models;

import java.util.Collection;
import java.util.Date;

import com.banana.BananaTaskManager.persistence.InMemoryRepository;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
	private long pid;

    @NonNull
    private String title;

    private Date initDate;
    
    private Date endDate;
    
    private User responsible;

    private InMemoryRepository repo;

    public Project(InMemoryRepository repo){
        this.repo=repo;
    }

    public void initProject(User user){
        this.responsible=user;
        this.initDate=new Date();
    }

    public void finishProject(){
        this.endDate= new Date();
    }


    public Project findProjectById(Long id) {
        return repo.findOneProject(id);
    }

    public Collection<Project> findAllProjects() {
        return repo.findAllProjects();
    }

    public Project addProject(Project project) {
        return repo.saveProject(project);
    }

    public void deleteProject(Long id) {
        Project project=repo.findOneProject(id);
        if(project!=null) repo.deleteProject(project);

    }


}
