package com.ericlai.express.dao;

import com.ericlai.express.dto.Poster;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int deleteByPrimaryKey(String posId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insert(Poster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int insertSelective(Poster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    Poster selectByPrimaryKey(String posId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKeySelective(Poster record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table poster
     *
     * @mbggenerated Tue Feb 02 19:49:20 CST 2016
     */
    int updateByPrimaryKey(Poster record);
}