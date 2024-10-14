package com.app.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.Action;
import com.app.Result;
import com.app.dao.MemberDAO;
import com.app.dao.OrderDAO;
import com.app.dao.ProductDAO;
import com.app.vo.OrderVO;

public class OrderWriteOkController implements Action {

	@Override
	public Result execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		Result result = new Result();
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = req.getSession();
		OrderVO orderVO = new OrderVO();
		OrderDAO orderDAO = new OrderDAO();
		ProductDAO productDAO = new ProductDAO();
		
		String memberEmail = (String)session.getAttribute("memberEmail");
		Long memberId = memberDAO.selectByEmail(memberEmail).getId();
		
//		Order Insert할 때 들어가야 하는 값
		orderVO.setMemberId(memberId);
		orderVO.setProductId(Long.parseLong(req.getParameter("productId")));
		orderVO.setProductCount(Integer.parseInt(req.getParameter("productCount")));
		
		productDAO.updateStock(orderVO);
		orderDAO.insert(orderVO);
		
		result.setRedirect(true);
		result.setPath("list.order?memberId=" + memberId);
		
		return result;
	}

}
