package com.kh.banzi.information.model.service;
import static com.kh.banzi.common.DBCP.getConnection;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.kh.banzi.user.model.dao.UserDAO;

public class InformationService {
	// DAO 생성
	private UserDAO dao;
	
	public InformationService() throws FileNotFoundException, IOException{
		dao = new UserDAO();
	}
}
