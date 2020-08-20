package es.bit.BananaTaskManager.models;

import es.bit.BananaTaskManager.persistence.InMemoryRepository;
import lombok.*;

import javax.validation.constraints.Null;
import java.util.Collection;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private long uid;

    @NonNull
    private String name;

    private String email;
    
    private String password;

    @Null
    private InMemoryRepository repo;

    public User(InMemoryRepository repo){
        this.repo=repo;
    }

    public String generateUniquePassword(){
        return "this_is_your_unique_password_sim";
    }

    public User findUserById(Long id) {
        return repo.findOneUser(id);
    }

    public Collection<User> findAllUsers() {
        return repo.findAllUsers();
    }

    public User addUser(User user) {
        return repo.saveUser(user);
    }

    public void deleteUser(Long id) {
        User user=repo.findOneUser(id);
        if(user!=null) repo.deleteUser(user);

    }
}
