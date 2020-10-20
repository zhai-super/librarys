package com.zyh.service.impl;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zyh.dao.UserBorrowInformationDao;
import com.zyh.dao.impl.UserBorrowInformationDaoImpl;
import com.zyh.entity.BookBorrowInformation;
import com.zyh.service.UserBorrowInformation;
import com.zyh.utils.PageBean;
import org.springframework.context.annotation.Bean;

public class UserBorrowInformationImpl implements UserBorrowInformation {
    private UserBorrowInformationDao dao = new UserBorrowInformationDaoImpl();
    @Override
    public PageBean<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book) {
        //book中的userid已经在pojo中做了处理 默认给一条假数据id=888
        PageBean<BookBorrowInformation> bean = dao.getUserBorrowInformation(book);
        return bean;
    }
}
