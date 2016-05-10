package com.ericlai.express.service;

import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dto.Person;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ERIC_LAI on 16/2/11.
 */

@Service
public class SysManageServiceImpl implements SysManageService{

    @Resource
    PersonMapper personMapper;

    @Override
    public void addPoster(Person record) {
        this.personMapper.insert(record);
    }

    @Override
    public List<Person> getPostman() {
        return this.personMapper.getPostman();
    }

    public int deletePostman(String personId) {
        return this.personMapper.deleteByPrimaryKey(personId);
    }
}
