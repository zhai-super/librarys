package com.zyh.dao.impl;
import com.zyh.dao.UserSeclectBookDao;
import com.zyh.entity.Book;
import com.zyh.entity.BookType;
import com.zyh.utils.PageBean;
import com.zyh.utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSeclectBookDaoImpl implements UserSeclectBookDao {
    @Override
    public  PageBean<Book> userSeclectBook(Book book, int current) {
        PageBean<Book> bean = new PageBean<>();
        ArrayList<Book> list = new ArrayList<>();
        int num = 0;
        //获取对象参数
        Integer bookId = book.getBookId();//书籍id
        String bookAuthor = book.getBookAuthor();//作者
        String bookName = book.getBookName();//书名
        BookType bookType = book.getBookTypeId();
        Integer bookTypeId = bookType.getBookTypeId();//书类型id
        //获取数据影响的行数
        int count = getChangeCount(bookId, bookAuthor, bookName, bookTypeId);
        //设置pageBean总数据数量
        bean.setTotalCount(count);
        //获取每页展示数据量
        Integer pageSize = bean.getPageSize();
        //对用户传来的当前页数进行校验
        bean.setCurrentPage(current);
        //获取正确的当前数据的页数
        Integer rightCurrentPage = bean.getCurrentPage();
        //编写SQL语句(查询书籍的描述位置,和书籍当前的本数,根据接收数据)
        StringBuffer strBuf = new StringBuffer("select book_name, book_describe ,book_count  from book_information where 1 = 1");
        if (bookId >= 1 && bookId <= 150) {
            strBuf.append(" and book_id = ? ");
        }
        if (bookAuthor != null && !" ".equals(bookAuthor)) {
            strBuf.append("  and book_author = ? ");
        }
        if (bookName != null && !" ".equals(bookName)) {
            strBuf.append("  and book_name = ? ");
        }
        if (bookTypeId >= 1 && bookTypeId <= 150) {
            strBuf.append("  and book_type_id = ? ");
        }
        //拼接分页语句
        strBuf.append(" limit ?," + pageSize);
        //完整SQL语句
        String sql = strBuf.toString();

        Connection conn = DbUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet re = null;
        Book books = null;

        try {
            ps = conn.prepareStatement(sql);
            //设置预执行参数
            if (bookId >= 1 && bookId <= 150) {
                ps.setObject(++num,bookId);
            }
            if (bookAuthor != null && !" ".equals(bookAuthor)) {
                ps.setObject(++num,bookAuthor);
            }
            if (bookName != null && !" ".equals(bookName)) {
                ps.setObject(++num,bookName);
            }
            if (bookTypeId >= 1 && bookTypeId <= 150) {
                ps.setObject(++num,bookTypeId);
            }
            //设置分页参数的开始位置
            Integer begin = (rightCurrentPage - 1) * pageSize;//分页查询偏移量
            ps.setObject(++num,begin);//设置分页参数1.参数2固定为pageSize
            //执行SQL
            re = ps.executeQuery();
            //遍历结果集
            while (re.next()) {
                books = new Book();
                String book_name = re.getString("book_name");
                String bookDescribe = re.getString("book_describe");//书籍描述
                int bookCount = re.getInt("book_count");//书籍数量
                books.setBookName(book_name);
                books.setBookCount(bookCount);
                books.setBookDscribe(bookDescribe);
                list.add(books);
            }
            list.forEach(str-> System.out.println(str));
            bean.setSelectUserDataA(list);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bean;
    }

    public int getChangeCount(Integer bookId,String bookAuthor,String bookName,Integer bookTypeId) {
        //获取查询语句影响的行数,根据行数获取分页数据
        StringBuffer strBuf = new StringBuffer("select count(*) from book_information where 1 = 1");
        if (bookId >= 1 && bookId <= 150) {
            strBuf.append(" and book_id = ? ");
        }
        if (bookAuthor != null && !" ".equals(bookAuthor)) {
            strBuf.append("  and book_author =  ? ");
        }
        if (bookName != null && !" ".equals(bookName)) {
            strBuf.append("  and book_name = ? ");
        }
        if (bookTypeId >= 1 && bookTypeId <= 150) {
            strBuf.append("  and book_type_id = ? ");
        }
        String sql = strBuf.toString();
        System.out.println(sql);
        int count = 0;//接收参数
        int num = 0;//记录参数
        Connection conn = DbUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet re = null;
        try {
            ps = conn.prepareStatement(sql);
            if (bookId >= 1 && bookId <= 150) {
                ps.setObject(++num,bookId);
            }
            if (bookAuthor != null && !" ".equals(bookAuthor)) {
                ps.setObject(++num,bookAuthor);
            }
            if (bookName != null && !" ".equals(bookName)) {
                ps.setObject(++num,bookName);
            }
            if (bookTypeId >= 1 && bookTypeId <= 150) {
                ps.setObject(++num,bookTypeId);
            }

            re = ps.executeQuery();
            while (re.next()) {
                count = re.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(re,ps,conn);
        }
        return count;//查询语句影响行数
    }
}
