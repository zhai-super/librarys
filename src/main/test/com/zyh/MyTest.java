package com.zyh;

import com.zyh.dao.impl.UserBorrowInformationDaoImpl;
import com.zyh.dao.impl.UserDaoImpl;

import com.zyh.dao.impl.UserSeclectBookDaoImpl;
import com.zyh.entity.BookBorrowInformation;
import com.zyh.service.impl.UserBorrowInformationImpl;
import com.zyh.utils.PageBean;
import com.zyh.entity.User;
import com.zyh.utils.Time;
import org.junit.Test;


import java.util.List;


public class MyTest {
    private UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void testTime() {
        String date = Time.borrowEndTime(150);
        System.out.println(date);
    }
    @Test
    public void getConn() {
        List<User> users = userDao.showAllData();
        System.out.println(users);

    }

    @Test
    public void insert() {
        User user = new User("mark", "123456", "男", 52);
        UserDaoImpl userDao = new UserDaoImpl();
        int count = userDao.insertUser(user);
        System.out.println(
                count
        );
    }

    @Test
    public void getCurrent() {
        UserDaoImpl userDao = new UserDaoImpl();
        PageBean<User> bean = userDao.selectLikeUser(2, 15, "李三", "男", 15);
        Integer page = bean.getCurrentPage();
        System.out.println(page);

    }

    @Test
    public void getTotalCount() {

        userDao.getTotalCount(0, "mark", "M", 0);
    }
    @Test
    public void testSQL() {
        UserDaoImpl dao = new UserDaoImpl();
        dao.updataUser(3, "翟宇豪", "", "", 150);
    }

    @Test
    public void getChangeCount() {
        UserSeclectBookDaoImpl dao = new UserSeclectBookDaoImpl();
        dao.getChangeCount(0, "jim", "", 0);
    }
    @Test
    public void test3() {
        BookBorrowInformation book = new BookBorrowInformation();
        book.setUser_id(1);
        UserBorrowInformationDaoImpl userBorrowInformation = new UserBorrowInformationDaoImpl();
        userBorrowInformation.getUserBorrowInformation(book);
    }
}
