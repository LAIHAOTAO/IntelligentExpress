package com.ericlai.express.dao;

import com.ericlai.express.dto.Person;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapper {

    /**
     * 获取所有的邮递员信息
     * @return
     */
    List<Person> getPostman();

    /**
     * 通过用户名获取密码
     * @param name
     * @return
     */
    String getPwByUserName(@Param("name")String name);

    /**
     * 通过用户名获取某个人的所有信息
     * @param name
     * @return
     */
    Person getPersonByUserName(@Param("name")String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int deleteByPrimaryKey(String personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insertSelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    Person selectByPrimaryKey(String personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKey(Person record);
}