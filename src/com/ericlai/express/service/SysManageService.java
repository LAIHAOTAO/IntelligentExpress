package com.ericlai.express.service;

import com.ericlai.express.dto.Person;

import java.util.List;

/**
 * Created by ERIC_LAI on 16/2/11.
 */
public interface SysManageService {

    void addPoster(Person record);

    List<Person> getPostman();

    int deletePostman(String personId);
}
