package com.sevenun.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sevenun.dao.AccountDao;
import com.sevenun.model.Account;
import com.sevenun.util.UploadHandle;

public class AccountController extends HttpServlet {
	
	private static String LIST_ACCOUNT = "/listAccount.jsp";
	private static String INSERT_OR_EDIT = "/account.jsp";
	private static String ERROR_PAGE = "/errorPage.jsp";
	private static AccountDao dao;
	
	public AccountController() {
		super();
		dao = new AccountDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String forward = "";
		String action = req.getParameter("action");
		if(action == null) action = "listAccount";
		
		if(action.equalsIgnoreCase("delete")) {
			int acc_id = Integer.parseInt(req.getParameter("acc_id"));
			dao.deleteAccount(acc_id);
			req.setAttribute("accounts", dao.getAllAccounts());
			forward = LIST_ACCOUNT;
		} else if(action.equalsIgnoreCase("update")) {
			int acc_id = Integer.parseInt(req.getParameter("acc_id"));
			Account account = dao.getAccountById(acc_id);
			req.setAttribute("account", account);
			forward = INSERT_OR_EDIT;
		} else if(action.equalsIgnoreCase("listAccount")) {
			req.setAttribute("accounts", dao.getAllAccounts());
			forward = LIST_ACCOUNT;
		} else {
			forward = INSERT_OR_EDIT;
		}
		
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Account account = new Account();
		String forward = LIST_ACCOUNT;
		String savePath = this.getServletContext().getRealPath("/pic");
		HashMap<String, String> map = UploadHandle.upload(savePath, req);
		

		String id = map.get("acc_id");
	
		account.setUsername(map.get("username"));
		account.setPassword(map.get("password"));
		account.setEmail(map.get("email"));
		
		if(id == null || id.isEmpty()) {
			if(map == null || map.isEmpty()) {
				forward = ERROR_PAGE;
				req.setAttribute("message", "上传文件失败！");
			} else {
				account.setProfilePath(map.get("profilePath"));
				dao.addAccount(account);
			}
		} else {
			account.setAcc_id(Integer.parseInt(id));
			String filename = map.get("profilePath");
			
			if(filename == null || filename.isEmpty() || filename.equalsIgnoreCase("pic\\")) {
				account.setProfilePath(dao.getAccountById(Integer.parseInt(id)).getProfilePath());
			}else {
				account.setProfilePath(map.get("profilePath"));
			}
			dao.updateAccount(account);
		}
		
		req.setAttribute("accounts", dao.getAllAccounts());
		RequestDispatcher view = req.getRequestDispatcher(forward);
		view.forward(req, resp);
	}

}
