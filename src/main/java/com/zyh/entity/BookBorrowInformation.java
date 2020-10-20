package com.zyh.entity;

import java.sql.Timestamp;
import java.util.Date;

/*借阅书籍实体类*/
public class BookBorrowInformation {
    /*
    * 书籍的id在动态查询的时候已经把书的信息全部发送到前端
    * 若是借阅者借阅当前id的书籍那么就根据id去书籍信息表去获取书籍的当前本书,以及书籍的名字
    * 判断本数是否何时
    * 判断当前用户的id 在借阅者信息表中出现的次数 >3则不能借书
    * 在借书之前判断用户是否存在尚未归还的图书
    * 若是尚未归还的图书同样超过三本的话则无法再继续借书
    *
    * 在执行借书插入语句时候,去更新借阅时间(数据库记录当前时间)
    **/
    private Integer book_id;//书籍id
    private Integer user_id;//借书人id
    private Timestamp begin_time;//借阅开始时间(数据自动记录当前数据的插入时间)
    private Integer borrow_day;//借书时长
    private String borrow_over_time;//借阅到期时间
    private Integer is_back;//是否归还

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        /*防止用户瞎几把乱在URL输入*/
        /*处理一下id*/
        if (user_id > 0) {
            this.user_id = user_id;
        } else {
            this.user_id = 888;//(id=888的是一条假信息)
        }
    }

    public Integer getIs_back() {
        return is_back;
    }

    public void setIs_back(Integer is_back) {
        this.is_back = is_back;
    }

    public Timestamp getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Timestamp begin_time) {
        this.begin_time = begin_time;
    }

    public Integer getBorrow_day() {
        return borrow_day;
    }

    public void setBorrow_day(Integer borrow_day) {
        this.borrow_day = borrow_day;
    }

    public String getBorrow_over_time() {
        return borrow_over_time;
    }

    public void setBorrow_over_time(String borrow_over_time) {

        this.borrow_over_time = borrow_over_time;
    }

    @Override
    public String toString() {
        return "BookBorrowInformation{" +
                "bookId=" + book_id +
                ", userId=" + user_id +
                ", begin_time='" + begin_time + '\'' +
                ", borrow_day=" + borrow_day +
                ", borrow_over_time='" + borrow_over_time + '\'' +
                '}';
    }
}
