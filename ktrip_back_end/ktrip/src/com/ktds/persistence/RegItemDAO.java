package com.ktds.persistence;

import com.ktds.domain.RegItemVO;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Scalar.String;

public class RegItemDAO {
	private HashMap<String, RegItemVO> dbMap;
	
	public RegItemDAO() {
		dbMap = new HashMap<String, RegItemVO>();
	}
	
	public boolean checkId() {
		
	}
	
	
}
