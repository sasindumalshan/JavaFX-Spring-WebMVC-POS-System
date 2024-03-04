package lk.interleon.pos.repo;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
public interface CategoryRepo extends JpaRepository<Category, String> {
    @Query(value = "SELECT * FROM category WHERE id LIKE ? OR name LIKE ?", nativeQuery = true)
    List<Category> findAllUnitLikeIdAndName(String id ,String name);
}
