package lk.interleon.pos.repo;

import lk.interleon.pos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */

public interface UserRepo extends JpaRepository<User, Long> {
}
