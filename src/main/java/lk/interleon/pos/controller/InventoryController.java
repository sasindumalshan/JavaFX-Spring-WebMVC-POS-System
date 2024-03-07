package lk.interleon.pos.controller;

import lk.interleon.pos.advisor.AppWideExceptionHandler;
import lk.interleon.pos.dto.InventoryDTO;
import lk.interleon.pos.service.InventoryService;
import lk.interleon.pos.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@RestController
@RequestMapping("/inventory")
@CrossOrigin
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getSupplier(Long id) {
        return new ResponseUtil("200", "ok", inventoryService.findInventory(id));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil save(@RequestBody InventoryDTO inventoryDTO) {
        try {
            inventoryService.save(inventoryDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs add date"));
        }
        return new ResponseUtil("200", "add Successful", inventoryDTO.getId());
    }

    @GetMapping(path = "findAll")
    public ResponseUtil findAll() {
        return new ResponseUtil("200", "ok", inventoryService.findAllInventory());
    }

    @GetMapping(path = "search")
    public ResponseUtil search(String id) {
        return new ResponseUtil("200", "ok", inventoryService.findAllInventoryLikeIdAndName(id));
    }

    @PostMapping(path = "update")
    public ResponseUtil update(@RequestBody InventoryDTO inventoryDTO) {
        System.out.println(inventoryDTO.toString());
        try {
            inventoryService.update(inventoryDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs update data"));
        }
        return new ResponseUtil("200", "update Successful", inventoryDTO.getId());
    }

    @DeleteMapping
    public ResponseUtil remove(Long id) {
        try {
            inventoryService.remove(id);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs Remove data"));
        }
        return new ResponseUtil("200", "remove Successful", id);
    }

    @GetMapping(path = "countByAll")
    public ResponseUtil countByAll() {
        return new ResponseUtil("200", "remove Successful", inventoryService.countByAll());
    }
}
