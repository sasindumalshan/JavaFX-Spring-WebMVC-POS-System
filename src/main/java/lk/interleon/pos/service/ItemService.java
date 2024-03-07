package lk.interleon.pos.service;

import lk.interleon.pos.dto.InventoryDTO;
import lk.interleon.pos.dto.ItemDTO;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */

public interface ItemService {
    void save(ItemDTO itemDTO);

    void update(ItemDTO itemDTO);

    void remove(Long id);

    ItemDTO findItem(Long id);

    List<ItemDTO> findAllItem();

    List<ItemDTO> findAllItemLikeIdAndName(String id);

    Long countByAll();

    boolean isExitsByCategory(Long id);


}
