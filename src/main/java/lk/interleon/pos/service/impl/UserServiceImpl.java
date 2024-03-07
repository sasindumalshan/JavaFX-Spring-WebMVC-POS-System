package lk.interleon.pos.service.impl;

import lk.interleon.pos.dto.UserDTO;
import lk.interleon.pos.entity.User;
import lk.interleon.pos.repo.UserRepo;
import lk.interleon.pos.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper mapper;
    @Override
    public void save(UserDTO userDTO) {
        userRepo.save(mapper.map(userDTO, User.class));
    }

    @Override
    public boolean verifyUser(String user_name, String password) {
        return userRepo.existsByUser_nameAndPassword(user_name,password);
    }

    @Override
    public void update(UserDTO userDTO) {
        if (!userRepo.existsById(Long.valueOf(userDTO.getId()))) {
            throw new RuntimeException(userDTO.getId() + "This User is not available, please check before update.!");
        }
        userRepo.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void remove(Long id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException(id + "This User is not available, please check before update.!");
        }
        userRepo.deleteById(Long.valueOf(id));
    }
}
