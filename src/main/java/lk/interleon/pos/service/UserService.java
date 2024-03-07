package lk.interleon.pos.service;

import lk.interleon.pos.dto.UserDTO;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */

public interface UserService {

    void save(UserDTO userDTO);

    boolean verifyUser(String user_name, String password);

    void update(UserDTO userDTO);

    void remove(Long id);
}
