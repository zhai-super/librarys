package com.zyh.service.impl;

import com.zyh.dao.UserDao;
import com.zyh.dao.impl.UserDaoImpl;
import com.zyh.utils.PageBean;
import com.zyh.entity.User;
import com.zyh.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private  UserDao dao = new UserDaoImpl();
    @Override
    //检查用户名是否存在
    public boolean checkUserName(User user) {
        String userName = user.getName();
        boolean b = dao.selectUserName(userName);
        if (b) {
            return true;//存在
        }
        return false;//不存在
    }

    @Override
    public boolean addUser(User user) {
        if (!checkUserName(user)) {

            dao.addUser(user);
            return true; //添加用户成功
        }
        return false;
    }

    @Override
    public boolean userLogin(String name, String password) {
        if (name != null && password != null) {
            boolean login = dao.userLogin(name, password);
            if (login) {
                System.out.println("登录成功");
                return true;
            }
        }
        System.out.println("登陆失败");
        return false;
    }

    @Override
    public List<User> showAllData() {
        List<User> list = dao.showAllData();
        if (list != null) {
            return list;
        }
        return null;
    }

    @Override
    public PageBean<User> selectLikeUser(int currentPage, int id, String name, String sex, int age) {
        PageBean<User> bean = dao.selectLikeUser(currentPage, id, name, sex, age);
        return bean;
    }
    //修改id的用户look状态为0
    @Override
    public int delUser(Integer id) {
        if (id != 0 && id < 100) {
            int count = dao.delUser(id);
            return count;//返回影响的行数
        }
        return 0;
    }

    @Override
    public int updataUser(int id, String name, String sex, String password, int age) {
        if (id != 0 && id < 100) {
            int count = dao.updataUser(id, name, sex, password, age);
            return count;
        }
        return 0;
    }
}
