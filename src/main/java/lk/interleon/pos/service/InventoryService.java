package lk.interleon.pos.service;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.InventoryDTO;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */

public interface InventoryService {
    void save(InventoryDTO inventoryDTO);

    void update(InventoryDTO inventoryDTO);

    void remove(Long id);

    InventoryDTO findInventory(Long id);

    List<InventoryDTO> findAllInventory();

    List<InventoryDTO> findAllInventoryLikeIdAndName(String id);

    Long countByAll();
}
