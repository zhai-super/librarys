package com.zyh.controller;

import com.zyh.agent.Resp;
import com.zyh.entity.Book;
import com.zyh.entity.BookType;
import com.zyh.utils.PageBean;
import com.zyh.service.UserSeclectBookService;
import com.zyh.service.impl.UserSeclectBookServiceImpl;
import com.zyh.utils.Send;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserSelectBook")
public class UserSelectBookInformationServlet extends HttpServlet {
    private UserSeclectBookService bookService = new UserSeclectBookServiceImpl();
    //用户查询书籍的信息
    //动态查询,根据书籍id,name,type,查询书籍的数量以及书籍的描述(描述的是的书籍地址)
    //同样做一个分页查询,使用pageaBean
    /*
    private Integer bookId;//书籍id
    private String bookName;//书名字
    private String bookAuthor;//作者
    private String bookCount;//书籍数量
    private BookType bookTypeId;//书籍类型id
    */

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设计相应参数格式
        Resp.setChAndCT(resp);
        int bookTypeId = 0;
        //获取前端传来的参数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));//当前页
        int bookId = Integer.parseInt(req.getParameter("bookId"));
        String bookName = req.getParameter("bookName");
        String bookAuthor = req.getParameter("bookAuthor");//书籍作者名字
        String typeId = req.getParameter("bookTypeId");
        if (typeId == null) {
            bookTypeId = 0;
        } else {
            bookTypeId = Integer.parseInt(typeId);
        }

        //  UserSelectBook?currentPage=2&bookId=0&bookAuthor=jim
        Book book = new Book(bookId, bookName, bookAuthor, new BookType(bookTypeId));
        PageBean<Book> bookPageBean = bookService.userSeclectBook(book, currentPage);
        if (bookPageBean != null) {
            Send<PageBean<Book>> pageBeanSend = Send.sendJsonSucess(bookPageBean);
            Resp.RespJson(resp, pageBeanSend);
            System.out.println("数据传送到前端");
        } else {
            Send<PageBean<Book>> pageBeanSend = Send.sendJsonError(bookPageBean);
            Resp.RespJson(resp,pageBeanSend);
            System.out.println("失败!!!");
        }
    }
}
