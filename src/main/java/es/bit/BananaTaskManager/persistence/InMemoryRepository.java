package es.bit.BananaTaskManager.persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import es.bit.BananaTaskManager.models.Project;
import es.bit.BananaTaskManager.models.Task;
import es.bit.BananaTaskManager.models.User;

public class InMemoryRepository {
	private List<User> userData = new ArrayList<>(Arrays.asList(
			new User(1,"ric","r@r.com","rc1", null),
			new User(2,"ana","a@a.com","ann2",null),
			new User(3,"gab","g@g.com","gab3", null)
	));

	private List<Project> projectData = new ArrayList<>(Arrays.asList(
			new Project(1,"App Twitter Interno",new Date(),null, userData.get(0),null),
			new Project(1,"App Finanzas",new Date(),null, userData.get(2),null)
	));

	private List<Task> taskData = new ArrayList<>(Arrays.asList(
			new Task(1,"iniciar proyecto",null,2,userData.get(0),0,projectData.get(0),null),
			new Task(2,"iniciar proyecto",new Date(),2,userData.get(0),2,projectData.get(1),null),
			new Task(2,"generar infraestructura",null,24,userData.get(2),0,projectData.get(1),null)
	));

	/* Users block */
	public User findOneUser(Long id) {
		Optional<User> userOptional = userData.stream().filter(user -> user.getUid()==id).findAny();
		return userOptional.orElse(null);
	}

	public Collection<User> findAllUsers() {
		return Collections.unmodifiableCollection(userData);
	}

	public User saveUser(User user) {
		Date date = new Date();
		user.setUid(date.getTime());
		userData.add(user);
		return user;
	}

	public void deleteUser(User user) {
		userData.remove(user);
	}

	/* Projects block */
	public Project findOneProject(Long id) {
		Optional<Project> projectOptional = projectData.stream().filter(project -> project.getPid()==id).findAny();
		return projectOptional.orElse(null);
	}

	public Collection<Project> findAllProjects() {
		return Collections.unmodifiableCollection(projectData);
	}

	public Project saveProject(Project project) {
		Date date = new Date();
		project.setPid(date.getTime());
		projectData.add(project);
		return project;
	}

	public void deleteProject(Project project) {
		projectData.remove(project);
	}

	/* Tasks block */
	public Task findOneTask(Long id) {
		Optional<Task> taskOptional = taskData.stream().filter(task -> task.getTid()==id).findAny();
		return taskOptional.orElse(null);
	}

	public Collection<Task> findAllTasks() {
		return Collections.unmodifiableCollection(taskData);
	}

	public Task saveTask(Task task) {
		Date date = new Date();
		task.setTid(date.getTime());
		taskData.add(task);
		return task;
	}

	public void deleteTask(Task task) {
		taskData.remove(task);
	}
}
