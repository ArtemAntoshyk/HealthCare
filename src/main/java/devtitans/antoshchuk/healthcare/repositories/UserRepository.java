package devtitans.antoshchuk.healthcare.repositories;

import devtitans.antoshchuk.healthcare.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
