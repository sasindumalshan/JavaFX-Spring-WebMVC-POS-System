package lk.interleon.pos.controller;

import lk.interleon.pos.advisor.AppWideExceptionHandler;
import lk.interleon.pos.dto.CategoryDTO;
import lk.interleon.pos.dto.UnitDTO;
import lk.interleon.pos.service.CategoryService;
import lk.interleon.pos.service.UnitService;
import lk.interleon.pos.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/3/2024
 */
@RestController
@RequestMapping("/unit")
@CrossOrigin
public class UnitController {
    @Autowired
    UnitService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getSupplier(Long id) {
        return new ResponseUtil("200", "ok", service.findUnit(id));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil save(@RequestBody UnitDTO unitDTO) {
        System.out.println(unitDTO.toString());
        try {
            service.save(unitDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs add date"));
        }
        return new ResponseUtil("200", "add Successful", unitDTO.getId());
    }

    @GetMapping(path = "findAll")
    public ResponseUtil findAll() {
        return new ResponseUtil("200", "ok", service.findAllUnit());
    }

    @PostMapping(path = "update")
    public ResponseUtil update(@RequestBody UnitDTO unitDTO) {
        try {
            service.update(unitDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs update data"));
        }
        return new ResponseUtil("200", "update Successful", unitDTO.getId());
    }

    @DeleteMapping
    public ResponseUtil remove(Long id) {
        try {
            service.remove(id);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs Remove data"));
        }
        return new ResponseUtil("200", "remove Successful", id);
    }

    @GetMapping(path = "search")
    public ResponseUtil search(String id) {
        return new ResponseUtil("200", "ok", service.findAllUnitLikeIdAndName(id));
    }

    @GetMapping(path = "countByAll")
    public ResponseUtil countByAll() {
        return new ResponseUtil("200", "remove Successful", service.countByAll());
    }
}
