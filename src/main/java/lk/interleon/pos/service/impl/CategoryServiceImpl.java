package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.entity.Category;
import lk.interleon.pos.entity.Item;
import lk.interleon.pos.repo.CategoryRepo;
import lk.interleon.pos.repo.ItemRepo;
import lk.interleon.pos.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    ItemRepo itemRepo;

    @Override
    public void save(CategoryDTO categoryDTO) {
        if (categoryRepo.existsById(Long.valueOf(categoryDTO.getId()))) {
            throw new RuntimeException(categoryDTO.getId() + " This Supplier is Already available");
        }
        categoryRepo.save(mapper.map(categoryDTO, Category.class));
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if (!categoryRepo.existsById(Long.valueOf(categoryDTO.getId()))) {
            throw new RuntimeException(categoryDTO.getId() + "This Supplier is not available, please check before update.!");
        }
        categoryRepo.save(mapper.map(categoryDTO, Category.class));
    }

    @Override
    public void remove(Long id) {
        boolean isExistsByCategory = itemRepo.existsByCategory_Id(id);
        System.out.println(isExistsByCategory);
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException(id + " This Category is not available, please check before delete.!");
        }
//        categoryRepo.delete(categoryRepo.getReferenceById(Long.valueOf(id)));

    }

    @Override
    public CategoryDTO findUnit(Long id) {
        return mapper.map(categoryRepo.findById(Long.valueOf(id)), CategoryDTO.class);

    }

    @Override
    public List<CategoryDTO> findAllUnit() {
        return mapper.map(categoryRepo.findAll(), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @Override
    public List<CategoryDTO> findAllUnitLikeIdAndName(String text) {
        return mapper.map(categoryRepo.findAllUnitLikeIdAndName(text + "%", text + "%"), new TypeToken<List<CategoryDTO>>() {
        }.getType());
    }

    @Override
    public Long countByAll() {
        return categoryRepo.count();
    }


}
