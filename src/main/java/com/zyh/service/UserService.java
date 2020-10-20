package com.zyh.service;

import com.zyh.utils.PageBean;
import com.zyh.entity.User;

import java.util.List;

public interface UserService {
    //检查用户名是否存在
    boolean checkUserName(User user);
    //添加用户
    boolean addUser(User user);
    //用户登录
    boolean userLogin(String name,String password);
    //后台展示用户信息
    List<User> showAllData();
    //模糊查询根据接收信息,返回给分页处理对象
    PageBean<User> selectLikeUser(int currentPage, int id, String name, String sex, int age);
    //根据id修改用户的状态(look = 0)
    int delUser(Integer id);
    //根据id去修改用户的信息
    int updataUser(int id, String name, String sex, String password, int age);
}
