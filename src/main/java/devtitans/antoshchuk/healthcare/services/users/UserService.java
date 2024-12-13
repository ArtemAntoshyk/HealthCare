package devtitans.antoshchuk.healthcare.services.users;

import devtitans.antoshchuk.healthcare.models.User;
import devtitans.antoshchuk.healthcare.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getIdByUsername(String username){
        return userRepository.findByUsername(username).get().getId();
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
