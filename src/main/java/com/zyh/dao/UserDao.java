package com.zyh.dao;

import com.zyh.utils.PageBean;
import com.zyh.entity.User;

import java.util.List;

public interface UserDao {
    //查询用户名是否存在
    boolean selectUserName(String name);
    //添加用户
    boolean addUser(User user);
    //用户登录
    boolean userLogin(String name, String password);

    List<User> showAllData();

    PageBean<User> selectLikeUser(int currentPage, int id, String name, String sex, int age);

    int delUser(Integer id);

    int updataUser(int id, String name, String sex, String password, int age);

}
