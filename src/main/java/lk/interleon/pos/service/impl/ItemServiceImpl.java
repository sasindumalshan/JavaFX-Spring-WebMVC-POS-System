package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.ItemDTO;
import lk.interleon.pos.entity.Category;
import lk.interleon.pos.entity.Item;
import lk.interleon.pos.entity.Unit;
import lk.interleon.pos.repo.CategoryRepo;
import lk.interleon.pos.repo.ItemRepo;
import lk.interleon.pos.repo.UnitRepo;
import lk.interleon.pos.service.ItemService;
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
public class ItemServiceImpl implements ItemService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    UnitRepo unitRepo;

    @Autowired
    ItemRepo itemRepo;

    @Override
    public void save(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getId())) {
            throw new RuntimeException(itemDTO.getId() + " This Item is Already available");
        }
        itemRepo.save(mapper.map(itemDTO, Item.class));
    }

    @Override
    public void update(ItemDTO itemDTO) {
        if (!itemRepo.existsById(itemDTO.getId())) {
            throw new RuntimeException(itemDTO.getId() + " This Item is not available");
        }
        itemRepo.save(mapper.map(itemDTO, Item.class));
    }

    @Override
    public void remove(Long id) {
        if (!itemRepo.existsById(Long.valueOf(id))) {
            throw new RuntimeException(id + " This Item is not available");
        }
        Item item = itemRepo.getReferenceById(Long.valueOf(id));
        itemRepo.delete(item);
    }

    @Override
    public ItemDTO findItem(Long id) {
        return mapper.map(itemRepo.findById(Long.valueOf(id)), ItemDTO.class);
    }

    @Override
    public List<ItemDTO> findAllItem() {
        return mapper.map(itemRepo.findAll(), new TypeToken<List<ItemDTO>>() {
        }.getType());
    }

    @Override
    public List<ItemDTO> findAllItemLikeIdAndName(String text) {
        return mapper.map(itemRepo.findAllUnitLikeIdAndName(text + "%", text + "%"), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @Override
    public Long countByAll() {
        return itemRepo.count();
    }

    @Override
    public boolean isExitsByCategory(Long id) {
        return itemRepo.existsByCategory_Id(id);
    }


}
