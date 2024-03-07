package lk.interleon.pos.controller;

import lk.interleon.pos.advisor.AppWideExceptionHandler;
import lk.interleon.pos.dto.ItemDTO;
import lk.interleon.pos.service.ItemService;
import lk.interleon.pos.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/5/2024
 */
@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil getSupplier(Long id) {
        return new ResponseUtil("200", "ok", itemService.findItem(id));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil save(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.save(itemDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs add date"));
        }
        return new ResponseUtil("200", "add Successful", itemDTO.getId());
    }

    @GetMapping(path = "findAll")
    public ResponseUtil findAll() {
        return new ResponseUtil("200", "ok", itemService.findAllItem());
    }

    @GetMapping(path = "search")
    public ResponseUtil search(String id) {
        return new ResponseUtil("200", "ok", itemService.findAllItemLikeIdAndName(id));
    }

    @PostMapping(path = "update")
    public ResponseUtil update(@RequestBody ItemDTO itemDTO) {
        try {
            itemService.update(itemDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs update data"));
        }
        return new ResponseUtil("200", "update Successful", itemDTO.getId());
    }

    @DeleteMapping
    public ResponseUtil remove(Long id) {
        try {
            itemService.remove(id);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs Remove data"));
        }
        return new ResponseUtil("200", "remove Successful", id);
    }

    @GetMapping(path = "countByAll")
    public ResponseUtil countByAll() {
        return new ResponseUtil("200", "remove Successful", itemService.countByAll());
    }
}
