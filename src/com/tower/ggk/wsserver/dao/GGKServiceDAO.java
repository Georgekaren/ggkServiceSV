package com.tower.ggk.wsserver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface GGKServiceDAO {

	public void insertLog(String inxml,String interfaceCode,String system_source,String system_target,String action, String status, String notes,String msgId,String esbId);
	public void signProjectCode(String orderCode,String itemCode);
	public void chgProjectStatus(String itemCode,String status);
	public void insertUser(HashMap<String,String> user);
	public void updateUser(HashMap<String,String> user);
	public void delUser(String userId);
	public String getUser(String userId);
	
	public List<Map<String, String>> getPropertyByObjectCode(String objectCode,String system);
	public List<Map<String, String>> getSystemByObjectCode(String objectCode,String function,String zeSysNo);
	public int excuteSql(String insql);
	public int getCountBySql(String insql);
	
}
