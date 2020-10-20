package com.zyh.controller;


import com.zyh.agent.Resp;

import com.zyh.entity.BookBorrowInformation;
import com.zyh.service.impl.UserBorrowInformationImpl;
import com.zyh.utils.PageBean;
import com.zyh.utils.Send;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/*
* 用户点击查询借阅历史显示当前用户(根据用户的id)的所用借阅历史*/
@WebServlet("/UserBorrowInformation")
public class UserBorrowInformationServlet extends HttpServlet {
    private UserBorrowInformationImpl service = null;
    private BookBorrowInformation book = null;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集
         Resp.setChAndCT(resp);
         service = new UserBorrowInformationImpl();
         book = new BookBorrowInformation();
        //展示出用户: 用户名  借阅书籍id  借阅书籍名称  到期时间
        //id为页面中当前用户的id
        int userId = Integer.parseInt(req.getParameter("userId"));
        book.setUser_id(userId);//后台做了id处理,若是瞎几把乱入书给一条假信息
        List<BookBorrowInformation> bean = service.getUserBorrowInformation(book);
        Send<List<BookBorrowInformation>> listSend = Send.sendJsonSucess(bean);
        Resp.RespJson(resp,listSend);
    }
}
