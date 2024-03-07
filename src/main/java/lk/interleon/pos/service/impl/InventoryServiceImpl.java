package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.InventoryDTO;
import lk.interleon.pos.dto.ItemDTO;
import lk.interleon.pos.entity.Inventory;
import lk.interleon.pos.entity.Item;
import lk.interleon.pos.repo.InventoryRepo;
import lk.interleon.pos.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    InventoryRepo inventoryRepo;
    @Autowired
    ModelMapper mapper;

    @Override
    public void save(InventoryDTO inventoryDTO) {
        if (inventoryRepo.existsById(inventoryDTO.getId())) {
            throw new RuntimeException(inventoryDTO.getId() + " This Item is Already available");
        }
        inventoryRepo.save(mapper.map(inventoryDTO, Inventory.class));
    }

    @Override
    public void update(InventoryDTO inventoryDTO) {
//        if (!inventoryRepo.existsById(inventoryDTO.getId())) {
//            throw new RuntimeException(inventoryDTO.getId() + " This Item is not available");
//        }
        inventoryRepo.save(mapper.map(inventoryDTO, Inventory.class));
    }

    @Override
    public void remove(Long id) {
        if (!inventoryRepo.existsById(Long.valueOf(id))) {
            throw new RuntimeException(id + " This Item is not available");
        }
        inventoryRepo.deleteById(id);
    }

    @Override
    public InventoryDTO findInventory(Long id) {
        return mapper.map(inventoryRepo.findById(id), InventoryDTO.class);
    }

    @Override
    public List<InventoryDTO> findAllInventory() {
        return mapper.map(inventoryRepo.findAll(), new TypeToken<List<InventoryDTO>>() {
        }.getType());
    }

    @Override
    public List<InventoryDTO> findAllInventoryLikeIdAndName(String text) {
        return mapper.map(inventoryRepo.findAllUnitLikeIdAndName(text + "%", text + "%"), new TypeToken<List<InventoryDTO>>() {
        }.getType());
    }

    @Override
    public Long countByAll() {
        return inventoryRepo.count();
    }
}
