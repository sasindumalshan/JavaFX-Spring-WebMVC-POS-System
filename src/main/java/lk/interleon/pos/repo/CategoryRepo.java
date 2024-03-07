package lk.interleon.pos.repo;

import lk.interleon.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM tbl_master_category WHERE id LIKE ? OR name LIKE ?", nativeQuery = true)
    List<Category> findAllUnitLikeIdAndName(String id, String name);
}
