package com.app.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.Result;
import com.app.member.controller.MemberJoinOkController;
import com.app.member.controller.MemberLoginOkController;

public class MemberFrontController extends HttpServlet{
   
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // URI 가져오기
      // URI 가져와서 컨테스트패스 날리고 필요한것만 가져오기
	  req.setCharacterEncoding("UTF-8");
	  resp.setContentType("text/html; charset-utf-8");
      String target = req.getRequestURI().replace(req.getContextPath() + "/","").split("\\.")[0];   
      Result result = null;
      
      if(target.equals("join")) {
    	  result = new Result();
    	  result.setPath("join.jsp");
      }else if(target.equals("join-ok")) {
    	  result = new MemberJoinOkController().execute(req, resp);
      }else if(target.equals("login")) {
    	  result = new Result();
    	  result.setPath("login.jsp");
      }else if(target.equals("login-ok")) {
    	  result = new MemberLoginOkController().execute(req, resp);
      }else if(target.equals("update-ok")) {
    	  
      }else if(target.equals("delete-ok")) {
    	  
      }else {
         // 전부 notfound 404
      }
      if(result !=null) {
         if(result.isRedirect()) {
//            리다이랙트
            resp.sendRedirect(result.getPath());
         }else {
//            포워드
            req.getRequestDispatcher(result.getPath()).forward(req, resp);
         }
      }
      
      
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doGet(req, resp);
   }

}
