package com.zyh.dao.impl;

import com.zyh.dao.UserBorrowInformationDao;
import com.zyh.entity.BookBorrowInformation;
import com.zyh.utils.DataUtils;
import com.zyh.utils.DbUtils;
import com.zyh.utils.PageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserBorrowInformationDaoImpl implements UserBorrowInformationDao {
    @Override
    public List<BookBorrowInformation >getUserBorrowInformation(BookBorrowInformation book) {
        Integer userId = book.getUser_id();
        //根据userId查询用户借书信息表
        String selectSql = "SELECT " +
                "user_id ,book_id ,borrow_over_time ,is_back " +
                "FROM" +
                " borrow_information " +
                "WHERE" +
                " `user_id`= ? ";
        Connection conn = DbUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet re = null;
        List<BookBorrowInformation> list = null;
        try {
            ps = conn.prepareStatement(selectSql);
            ps.setObject(1,userId);
            re = ps.executeQuery();
            list = DataUtils.getAll(BookBorrowInformation.class, re);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.colse(re,ps,conn);
        }
        return list;
    }
}
