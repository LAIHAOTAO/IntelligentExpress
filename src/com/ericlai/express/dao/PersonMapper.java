package com.ericlai.express.dao;

import com.ericlai.express.dto.Person;
import com.ericlai.express.dto.PersonExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonMapper {

    String getPwByUserName(@Param("name")String name);

    Person getPersonByUserName(@Param("name")String name);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int countByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int deleteByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int deleteByPrimaryKey(String personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int insert(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int insertSelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    List<Person> selectByExample(PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    Person selectByPrimaryKey(String personId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int updateByPrimaryKeySelective(Person record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table person
     *
     * @mbggenerated Tue Jan 26 00:21:57 CST 2016
     */
    int updateByPrimaryKey(Person record);
}