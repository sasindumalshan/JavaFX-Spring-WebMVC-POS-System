package lk.interleon.pos.repo;

import lk.interleon.pos.entity.Inventory;
import lk.interleon.pos.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */

public interface InventoryRepo extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT * FROM tbl_master_inventory WHERE id LIKE ? OR item LIKE  ?", nativeQuery = true)
    List<Item> findAllUnitLikeIdAndName(String id, String name);
}
