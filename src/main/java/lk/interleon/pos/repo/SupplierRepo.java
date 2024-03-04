package lk.interleon.pos.repo;

import lk.interleon.pos.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
public interface SupplierRepo extends JpaRepository<Supplier, String> {
    @Query(value = "SELECT * FROM supplier WHERE id LIKE ? OR name LIKE ?", nativeQuery = true)
    List<Supplier> findAllUnitLikeIdAndName(String id, String name);

}
