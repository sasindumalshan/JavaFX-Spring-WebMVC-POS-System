package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.UserDTO;
import lk.interleon.pos.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/6/2024
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public void save(UserDTO userDTO) {

    }

    @Override
    public Object verifyUser(String user_name, String password) {
        return null;
    }

    @Override
    public void update(UserDTO userDTO) {

    }

    @Override
    public void remove(Long id) {

    }
}
