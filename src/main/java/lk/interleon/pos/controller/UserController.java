package lk.interleon.pos.controller;

import lk.interleon.pos.advisor.AppWideExceptionHandler;
import lk.interleon.pos.dto.UnitDTO;
import lk.interleon.pos.dto.UserDTO;
import lk.interleon.pos.service.UserService;
import lk.interleon.pos.utility.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} ,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil verifyUser(String user_name,String password) {
        return new ResponseUtil("200", "ok", userService.verifyUser(user_name,password));
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseUtil save(@RequestBody UserDTO userDTO) {
        try {
            userService.save(userDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs User Registration"));
        }
        return new ResponseUtil("200", "add Successful", userDTO.getId());
    }

    @PostMapping(path = "update")
    public ResponseUtil update(@RequestBody UserDTO userDTO) {
        try {
            userService.update(userDTO);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs update User"));
        }
        return new ResponseUtil("200", "update Successful", userDTO.getId());
    }

    @DeleteMapping
    public ResponseUtil remove(Long id) {
        try {
            userService.remove(id);
        } catch (Exception e) {
            return new AppWideExceptionHandler().handleAllRuntimeExceptions(new RuntimeException("Something wrongs Remove User"));
        }
        return new ResponseUtil("200", "remove Successful", id);
    }

}
