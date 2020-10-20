package com.zyh.controller;
import com.alibaba.fastjson.JSONObject;
import com.zyh.utils.PageBean;
import com.zyh.entity.User;
import com.zyh.service.UserService;
import com.zyh.service.impl.UserServiceImpl;
import com.zyh.utils.Send;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
    /*功能集成页面(客户端)(后端)*/
    //获取用户参数:用户名,密码,性别,年龄
    private UserService service = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=utf-8");

        String action = req.getParameter("action");
        switch(action){
      case "register"://注册
          register(req,resp);
          resp.getWriter().println("success");
      break;
      case "login"  ://登录
          login(req);
          resp.getWriter().println("success");
      break;
      case "showAllData"  ://显示全部用户信息
          showAllData(resp);
          System.out.println("OK!!");
      break;
      case "selectLike"  ://动态查询用户信息
          selectLikeUser(req,resp);
      break;
       case "delUser"  ://删除用户信息(实质上是改变用户的look状态为0)
            delUser(req,resp);
      break;
       case "updataUser"  ://修改用户信息 ,根据用户的id修改信息,id不能修改,其他权限全部可以修改
            updataUser(req,resp);
      break;
      }
    }
    //修改用户信息 ,根据用户的id修改信息,id不能修改,其他权限全部可以修改
    private void updataUser(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));//根据id去修改
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String password = req.getParameter("password");
        int age = Integer.parseInt(req.getParameter("age"));
        int count = service.updataUser(id, name, sex, password, age);
        System.out.println("修改用户信息成功,影响数据("+count+")行");
    }

    private void delUser(HttpServletRequest req, HttpServletResponse resp) {
        //删除选中的用户信息(实质上是改变用户的look状态为0)
        int anInt = Integer.parseInt(req.getParameter("id"));
        service.delUser(anInt);
        try {
            resp.getWriter().println("删除成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //模糊查询根据用户所传的 currentPage id 名字 年龄 性别 组合查询;
   public void selectLikeUser(HttpServletRequest req, HttpServletResponse resp) {
       //currentPage默认为1
       int currentPage = Integer.parseInt(req.getParameter("currentPage"));
       int id = Integer.parseInt(req.getParameter("id"));
       String name = req.getParameter("name");
       String sex = req.getParameter("sex");
       int age = Integer.parseInt(req.getParameter("age"));
       //service操作
       PageBean<User> bean = service.selectLikeUser(currentPage, id, name, sex, age);
       List<User> data = bean.getSelectUserDataA();
       data.forEach(str->{
           System.out.println(str);
       });
       System.out.println("ok-----");
       //将获取到的信息发送到前端
       if (bean != null) {
           Send<PageBean<User>> sendJsonSucess = Send.sendJsonSucess(bean);//包装数据
           String jsonString = JSONObject.toJSONString(sendJsonSucess);//对象转换json数据
           try {
               resp.getWriter().println(jsonString);
           } catch (IOException e) {
               System.out.println("json数据发送失败");
               e.printStackTrace();
           }
       } else {
           Send<PageBean<User>> sendJsonError = Send.sendJsonError(bean);
           try {
               resp.getWriter().println(sendJsonError);
           } catch (IOException e) {
               System.out.println("json数据发送失败");
               e.printStackTrace();
           }
       }

   }

    //注册功能
    public void register(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        int anInt = Integer.parseInt(age);
        User user = new User(name,password,sex,anInt);
        service.addUser(user);
        System.out.println("添加用户成功");
    }

    //登录功能
    public void login(HttpServletRequest request) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        service.userLogin(name,password);
    }
    //展示全部用户信息到前端
    public void showAllData(HttpServletResponse response) {
        List<User> list = service.showAllData();
        if (list != null) {
            Send<List<User>> listSend = Send.sendJsonSucess(list);
            System.out.println(listSend);
            String json = JSONObject.toJSONString(listSend);
            /*发送json到浏览器有问题*/
            Class<? extends Send> jsonClass = listSend.getClass();
            try {
                response.getWriter().println(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Send<List<User>> jsonError = Send.sendJsonError(list);
            String jsonString = JSONObject.toJSONString(jsonError);
            try {
                response.getWriter().println(jsonString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
