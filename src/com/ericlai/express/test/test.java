package com.ericlai.express.test;

import com.ericlai.express.dao.PersonMapper;
import com.ericlai.express.dto.Person;
import com.ericlai.express.service.LoginService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.Reader;

/**
 * Created by Administrator on 2016/1/25.
 */
public class test {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    @Resource
    private static LoginService loginService = null;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatisConfig.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //核心，注册我们新建的服务
//			sqlSessionFactory.getConfiguration().addMapper(PersonMapper.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            String name = "管理员0";
            String pw = personMapper.getPwByUserName(name);
            System.out.println(pw);
//            Person person = personMapper.selectByPrimaryKey("20160125184423");
//            System.out.println(person.getName());
            session.commit() ;
        } finally {
            session.close();
        }
    }
}
