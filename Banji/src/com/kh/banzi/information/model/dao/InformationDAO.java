package com.kh.banzi.information.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class InformationDAO {
	// 프로퍼티 생성
	private Properties prop = null;
	
	public InformationDAO() throws FileNotFoundException, IOException{
		String fileName = InformationDAO.class.getResource("/com/kh/banzi/sql/information-query.properties").getPath();
		
		prop = new Properties();
		prop.load(new FileReader(fileName));
	}
	
}
