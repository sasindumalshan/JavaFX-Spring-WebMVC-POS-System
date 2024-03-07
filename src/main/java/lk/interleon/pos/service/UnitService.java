package lk.interleon.pos.service;

import lk.interleon.pos.dto.UnitDTO;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */

public interface UnitService {
    void save(UnitDTO unitDTO);

    void update(UnitDTO unitDTO);

    void remove(Long id);

    UnitDTO findUnit(Long id);

    List<UnitDTO> findAllUnit();

    List<UnitDTO> findAllUnitLikeIdAndName(String id);

    Long countByAll();
}
