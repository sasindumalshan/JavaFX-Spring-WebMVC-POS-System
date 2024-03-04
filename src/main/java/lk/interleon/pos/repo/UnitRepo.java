package lk.interleon.pos.repo;

import lk.interleon.pos.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
@EnableJpaRepositories
public interface UnitRepo extends JpaRepository<Unit, String> {
    @Query(value = "SELECT * FROM unit WHERE id LIKE ? OR name LIKE ?", nativeQuery = true)
    List<Unit> findAllUnitLikeIdAndName(String id, String name);

}
