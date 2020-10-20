package com.zyh.service.impl;



import com.zyh.dao.UserBorrowInformationDao;
import com.zyh.dao.impl.UserBorrowInformationDaoImpl;
import com.zyh.entity.BookBorrowInformation;
import com.zyh.service.UserBorrowInformation;



import java.util.List;

public class UserBorrowInformationImpl implements UserBorrowInformation {
    private UserBorrowInformationDao dao = new UserBorrowInformationDaoImpl();
    @Override
    public List<BookBorrowInformation> getUserBorrowInformation(BookBorrowInformation book) {
        //book中的userid已经在pojo中做了处理 默认给一条假数据id=888
        List<BookBorrowInformation> bean = dao.getUserBorrowInformation(book);
        return bean;
    }
}
