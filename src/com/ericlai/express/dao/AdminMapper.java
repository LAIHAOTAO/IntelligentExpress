package com.ericlai.express.dao;

import com.ericlai.express.dto.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int deleteByPrimaryKey(String adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    Admin selectByPrimaryKey(String adminId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table admin
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKey(Admin record);
}