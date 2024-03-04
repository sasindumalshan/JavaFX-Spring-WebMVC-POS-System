package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.UnitDTO;
import lk.interleon.pos.entity.Category;
import lk.interleon.pos.entity.Unit;
import lk.interleon.pos.repo.UnitRepo;
import lk.interleon.pos.service.UnitService;
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
public class UnitServiceImpl implements UnitService {
    @Autowired
    ModelMapper mapper;

    @Autowired
    UnitRepo repo;

    @Override
    public void save(UnitDTO unitDTO) {
        if (repo.existsById(unitDTO.getId())) {
            throw new RuntimeException(unitDTO.getId() + " This Supplier is Already available");
        }
        repo.save(mapper.map(unitDTO, Unit.class));
    }

    @Override
    public void update(UnitDTO unitDTO) {
        if (!repo.existsById(unitDTO.getId())) {
            throw new RuntimeException(unitDTO.getId() + "This Supplier is not available, please check before update.!");
        }
        repo.save(mapper.map(unitDTO, Unit.class));
    }

    @Override
    public void remove(String id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException(id + " This Unit is not available, please check before delete.!");
        }
        repo.deleteById(id);
    }

    @Override
    public UnitDTO findUnit(String id) {
        return mapper.map(repo.findById(id), UnitDTO.class);
    }

    @Override
    public List<UnitDTO> findAllUnit() {
        return mapper.map(repo.findAll(), new TypeToken<List<UnitDTO>>() {
        }.getType());
    }

    @Override
    public List<UnitDTO> findAllUnitLikeIdAndName(String text) {
        return mapper.map(repo.findAllUnitLikeIdAndName(text + "%", text + "%"), new TypeToken<List<UnitDTO>>() {
        }.getType());
    }

    @Override
    public String countByAll() {
        return String.valueOf(repo.count());
    }
}
