package com.zyh.dao.impl;

import com.zyh.dao.UserDao;
import com.zyh.utils.PageBean;
import com.zyh.entity.User;

import com.zyh.utils.DbUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //根据名字查询用户名是否存在
    @Override
    public boolean selectUserName(String name) {
        PreparedStatement ps = null;
        ResultSet re = null;
        Connection conn = DbUtils.getConnection();
            try {
                String sql = "SELECT * FROM user_register WHERE name = ? ";
                ps = conn.prepareStatement(sql);
                ps.setObject(1,name);
                re = ps.executeQuery();
                while (re.next()) {
                    if (re.getString("name").equals(name)) {
                        return true;//名字存在
                    }
                }
            } catch (SQLException throwables) {
                System.out.println("sql错误");
                throwables.printStackTrace();
            }finally {
                DbUtils.colse(re,ps,conn);
            }
        return false;
    }

    @Override
    //添加用户
    public boolean addUser(User user) {
        if (user != null) {
            insertUser(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean userLogin(String name, String password) {
        String sql = "select id,name,password,sex,age from user_register where name = ? and password = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,name);
            ps.setObject(2,password);
            ResultSet re = ps.executeQuery();
            while (re.next()) {
                String s = re.getString("name");
                String p = re.getString("password");
                if (s.equals(name) && p.equals(password)) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(null,ps,conn);
        }
        return false;
    }

    @Override
    public List<User> showAllData() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "select id,name,sex,age from user_register where look = ?";
        Connection connection = DbUtils.getConnection();
        PreparedStatement ps = null;
        ResultSet re = null;
        User user = null;
        try {
                ps = connection.prepareStatement(sql);
                ps.setObject(1,1);
                re = ps.executeQuery();
            while (re.next()) {
                int id = re.getInt("id");
                String name = re.getString("name");
                String sex = re.getString("sex");
                int age = re.getInt("age");
                user = new User(id, name, sex, age);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(re,ps,connection);
        }
        return list;
    }

    @Override
    public PageBean<User> selectLikeUser(int currentPage, int id, String name, String sex, int age) {
        PageBean<User> bean = new PageBean<>();
        ArrayList<User> list = new ArrayList<>();
        int num = 0;//记录参数执行的顺序
        //判断currentPage之前要先获取总数页数,所以在这之前要先获取总数据条数
        int totalCount = getTotalCount(id, name, sex, age);//得到数据影响的总行数
        bean.setTotalCount(totalCount);//设置操作影响的数据总行数
        bean.setCurrentPage(currentPage);//排除URL恶意操作
        Integer rightCurrentPage = bean.getCurrentPage();//过滤后正确的当前页数
        //计算分页数据的条数
        Integer pageSize = bean.getPageSize();//获取每页展示的数据总数 写死=3
        Integer begin = (rightCurrentPage - 1) * pageSize;//分页查询偏移量
        //如果影响行数=0则操作无效
        if (totalCount == 0) {
            return null;//无效操作
        }
        //分页查询
        StringBuffer strBuf = new StringBuffer("select id,name,sex,age from user_register where 1=1 ");
        //拼接sql语句
        if (id != 0 && id < 100) {
            strBuf.append("  and id =  ?");
        }
        if (name != null && !"".equals(name.trim())) {
            strBuf.append("  and name like  ?");
        }
        if (sex != null && !"".equals(sex.trim())) {
            strBuf.append("  and sex  =  ?");
        }
        if (age != 0) {
            strBuf.append("  and age  >  ?");
        }
        strBuf.append(" limit ?,?");
        String sql = strBuf.toString();//转sql语句
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet re = null;
        User user = null;
        try {
            conn = DbUtils.getConnection();
             ps = conn.prepareStatement(sql);
            //设置参数的执行顺序
            if (id != 0) {
                strBuf.append("  and id =  ?");
                ps.setObject(++num,id);
            }
            if (name != null && !"".equals(name.trim())) {
                strBuf.append("  and name like  ?  ");
                ps.setObject(++num,"%"+name+"%");
            }
            if (sex != null && !"".equals(sex.trim())) {
                strBuf.append("  and sex  =  ?");
                ps.setObject(++num,sex);
            }
            if (age != 0) {
                strBuf.append("  and age  >  ?");
                ps.setObject(++num,age);
            }
            //分页
            ps.setObject(++num,begin);
            ps.setObject(++num,pageSize);
            //执行Sql
            re = ps.executeQuery();
            //得到的结果存到user对象中添加到集合
            while (re.next()) {
                int id1 = re.getInt("id");
                String name1 = re.getString("name");
                String sex1 = re.getString("sex");
                int age1 = re.getInt("age");
                user = new User(id1, name1, sex1, age1);
                list.add(user);
            }
            //得到的数据给pageBean对象的selectUserData(按条件查询所得到的用户信息)
            bean.setSelectUserDataA(list);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(re,ps,conn);
        }
        return bean;//对象里面有全部查询的信息
    }

    @Override
    public int delUser(Integer id) {
        //改变当前id的用户look的状态
        int count = 0;//记录影响行数
        String sql = "UPDATE user_register SET look = 0 WHERE id = ? ;";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn =  DbUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setObject(1,id);
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(null,ps,conn);
        }
        return count;
    }

    @Override
    public int updataUser(int id, String name, String sex, String password, int age) {
       /* 根据id去修改用户的信息*/
        int count = 0;//记录sql语句影响的行数
        int num = 1;//记录参数执行的顺序
        StringBuffer strBuf = new StringBuffer("UPDATE user_register SET id = ? ");
        //编写Sql语句(动态的执行修改操作)
        if (name != null && !"".equals(name)) {
            strBuf.append(" ,name = ?  ");
        }
        if (sex != null && !"".equals(sex)) {
            strBuf.append(" ,sex = ?  ");
        }
        if (password != null && !"".equals(password)) {
            strBuf.append(" ,password = ?  ");
        }
        if ( age<100 && age > 0) {
            strBuf.append(" ,age = ?  ");
        }
        strBuf.append("  where id ="+id);
        String sql = strBuf.toString();

        Connection conn = DbUtils.getConnection();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            //动态设置?的值
            ps.setObject(1,id);

            if (name != null && !"".equals(name)) {
                ps.setObject(++num,name);
            }
            if (sex != null && !"".equals(sex)) {
                ps.setObject(++num,sex);
            }
            if (password != null && !"".equals(password)) {
                ps.setObject(++num,password);
            }
            if ( age<100 && age > 0) {
                ps.setObject(++num,age);
            }
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("sql语句出错了");
            throwables.printStackTrace();
        }finally {
            DbUtils.colse(null,ps,conn);
        }
        System.out.println("updateUser成功");
        return count;
    }


    public int insertUser(User user) {
        int count = 0;
        PreparedStatement ps = null;
        String sql = "insert into user_register(name,password,sex,age)values(?,?,?,?)";
        try {
            ps = DbUtils.getConnection().prepareStatement(sql);
            ps.setObject(1, user.getName());
            ps.setObject(2, user.getPassword());
            ps.setObject(3, user.getSex());
            ps.setObject(4, user.getAge());
            count = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.colse(null, ps, null);
        }
        return count;
    }
    /*获取迷糊查询影响的数据总条数*/
        public int getTotalCount(int id, String name, String sex, int age) {
            int num = 0;//记录执行的顺序
            int totalCount = 0;//记录影响的数据行数
            StringBuffer strBuf = new StringBuffer("select count(*) from user_register where 1 = 1");
            if (id != 0) {
                strBuf.append("  and id =  ?");
            }
            if (name != null && !"".equals(name.trim())) {
                strBuf.append("  and name like  ?");
            }
            if (sex != null && !"".equals(sex.trim())) {
                strBuf.append("  and sex  =  ?");
            }
            if (age != 0) {
                strBuf.append("  and age  >  ?");
            }
            String sql = strBuf.toString();//完整sql语句
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet re = null;

            try {
                conn = DbUtils.getConnection();
                 ps  = conn.prepareStatement(sql);
                 //设置参数的执行顺序
                if (id != 0) {
                    strBuf.append("  and id =  ?");
                    ps.setObject(++num,id);
                }
                if (name != null && !"".equals(name.trim())) {
                    strBuf.append("  and name like  ?  ");
                    ps.setObject(++num,"%"+name+"%");
                }
                if (sex != null && !"".equals(sex.trim())) {
                    strBuf.append("  and sex  =  ?");
                    ps.setObject(++num,sex);
                }
                if (age != 0) {
                    strBuf.append("  and age  >  ?");
                    ps.setObject(++num,age);
                }
                    re = ps.executeQuery();
                while (re.next()) {
                    totalCount = re.getInt(1);
                }

            } catch (SQLException throwables) {
                System.out.println("sql语句有错");
                throwables.printStackTrace();
            }
            finally {
                DbUtils.colse(re,ps,conn);
            }
            System.out.println(
                    "影响了("+totalCount+")行"
            );
            return totalCount;
        }

    }

