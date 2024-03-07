package lk.interleon.pos.service;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.SupplierDTO;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */

public interface SupplierService {
    void save(SupplierDTO supplierDTO);

    void update(SupplierDTO supplierDTO);

    void remove(Long id);

    SupplierDTO findUnit(Long id);

    List<SupplierDTO> findAllUnit();

    List<SupplierDTO> findAllSupplierLikeIdAndName(String text);

    Long countByAll();
}
