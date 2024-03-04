package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.entity.Category;
import lk.interleon.pos.repo.CategoryRepo;
import lk.interleon.pos.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public void save(CategoryDTO categoryDTO) {
        if (categoryRepo.existsById(categoryDTO.getId())) {
            throw new RuntimeException(categoryDTO.getId() + " This Supplier is Already available");
        }
        categoryRepo.save(mapper.map(categoryDTO, Category.class));
    }

    @Override
    public void update(CategoryDTO categoryDTO) {
        if (!categoryRepo.existsById(categoryDTO.getId())) {
            throw new RuntimeException(categoryDTO.getId() + "This Supplier is not available, please check before update.!");
        }
        categoryRepo.save(mapper.map(categoryDTO, Category.class));
    }

    @Override
    public void remove(String id) {
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException(id + " This Category is not available, please check before delete.!");
        }
        categoryRepo.deleteById(id);
    }

    @Override
    public CategoryDTO findUnit(String id) {
        return mapper.map(categoryRepo.findById(id), CategoryDTO.class);

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
    public String countByAll() {
        return String.valueOf(categoryRepo.count());
    }
}
