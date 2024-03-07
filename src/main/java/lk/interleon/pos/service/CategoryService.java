package lk.interleon.pos.service;

import lk.interleon.pos.dto.CategoryDTO;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */

public interface CategoryService {
    void save(CategoryDTO categoryDTO);

    void update(CategoryDTO categoryDTO);

    void remove(Long id);

    CategoryDTO findUnit(Long id);

    List<CategoryDTO> findAllUnit();

    List<CategoryDTO> findAllUnitLikeIdAndName(String id);

    Long countByAll();
}
