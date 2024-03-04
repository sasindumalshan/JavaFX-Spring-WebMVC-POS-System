package lk.interleon.pos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author Sasindu Malshan
 * @project Interleaon
 * @date 3/4/2024
 */
@Service
@Transactional
public class SupplierDetailsServiceImpl {
    @Autowired
    ModelMapper mapper;
}
