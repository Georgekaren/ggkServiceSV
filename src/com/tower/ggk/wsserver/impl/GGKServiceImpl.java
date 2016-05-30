package com.tower.ggk.wsserver.impl; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.dom4j.Document;
import org.dom4j.Node;

import com.tower.ggk.wsserver.IGGKService;
import com.tower.ggk.wsserver.client.GGKSendMsgClient;
import com.tower.ggk.wsserver.dao.GGKServiceDAO;
import com.tower.ggk.wsserver.service.UpdateRuleService;
import com.tower.ggk.wsserver.utils.VerifyReturnVO;
import com.tower.ggk.wsserver.utils.XMLUtil;


public class GGKServiceImpl  implements IGGKService { 

	private GGKServiceDAO ggkServiceDAO;
	private GGKSendMsgClient ggkClient;
	private UpdateRuleService updateRuleService;
	
	public UpdateRuleService getUpdateRuleService() {
		return updateRuleService;
	}

	public void setUpdateRuleService(UpdateRuleService updateRuleService) {
		this.updateRuleService = updateRuleService;
	}

	public GGKSendMsgClient getGgkClient() {
		return ggkClient;
	}

	public void setGgkClient(GGKSendMsgClient ggkClient) {
		this.ggkClient = ggkClient;
	}

	public GGKServiceDAO getGgkServiceDAO() {
		return ggkServiceDAO;
	}

	public void setGgkServiceDAO(GGKServiceDAO ggkServiceDAO) {
		this.ggkServiceDAO = ggkServiceDAO;
	}

	
	/* (non-Javadoc)
	 * @see com.tower.ggk.wsserver.IEmpService#MT_CPMIS2OA_Req(org.apache.axiom.om.OMElement)
	 * 自定义Webservice，用于兼容 对象格式入参。 接口范例   入参名：MT_CPMIS2OA_Req  回参名：MT_CPMIS2OA_Resp
	 */
	public OMElement MT_CPMIS2OA_Req(OMElement soapBody) {
		// logger.info("----------------ok--------------"+soapBody.getText());
		OMFactory fac = OMAbstractFactory.getOMFactory();
		String sMSGID = "";
		String sPMSGID = "";
		String sSENDTIME = "";
		String sS_PROVINCE = "";
		String sS_SYSTEM = "";
		String sSERVICENAME = "";
		String sT_PROVINCE = "";
		String sT_SYSTEM = "";
		String sRETRY = "";
		String sMESSAGE = "";

		OMElement in_I_REQUEST = soapBody.getFirstElement();

		OMElement in_BASEINFO = in_I_REQUEST.getFirstElement();
		Iterator it = in_BASEINFO.getChildElements();
		while (it.hasNext()) {
			OMElement it0 = (OMElement) it.next();
			String tname = it0.getLocalName();
			String tvalue = it0.getText();
			System.out.println(tname);
			if ("MSGID".equals(tname)) {
				sMSGID = tvalue;
			} else if ("PMSGID".equals(tname)) {
				sPMSGID = tvalue;
			} else if ("SENDTIME".equals(tname)) {
				sSENDTIME = tvalue;
			} else if ("S_PROVINCE".equals(tname)) {
				sS_PROVINCE = tvalue;
			} else if ("S_SYSTEM".equals(tname)) {
				sS_SYSTEM = tvalue;
			} else if ("SERVICENAME".equals(tname)) {
				sSERVICENAME = tvalue;
			} else if ("T_PROVINCE".equals(tname)) {
				sT_PROVINCE = tvalue;
			} else if ("T_SYSTEM".equals(tname)) {
				sT_SYSTEM = tvalue;
			} else if ("RETRY".equals(tname)) {
				sRETRY = tvalue;
			} else if ("MESSAGE".equals(tname)) {
				sMESSAGE = tvalue;
			}
		}

		String tns = "urn:DefaultNamespace";
		OMNamespace omNs = fac.createOMNamespace(tns, "urn");

		OMElement method = fac.createOMElement("MT_CPMIS2OA_Resp", omNs);

		OMElement I_REQUEST = fac.createOMElement("E_RESPONSE", null);

		OMElement BASEINFO = fac.createOMElement("BASEINFO", null);

		OMElement MSGID = fac.createOMElement("MSGID", null);
		MSGID.addChild(fac.createOMText(MSGID, sMSGID));
		BASEINFO.addChild(MSGID);

		OMElement PMSGID = fac.createOMElement("PMSGID", null);
		PMSGID.addChild(fac.createOMText(PMSGID, sPMSGID));
		BASEINFO.addChild(PMSGID);

		OMElement SENDTIME = fac.createOMElement("SENDTIME", null);
		SENDTIME.addChild(fac.createOMText(SENDTIME, sSENDTIME));
		BASEINFO.addChild(SENDTIME);

		OMElement S_PROVINCE = fac.createOMElement("S_PROVINCE", null);
		S_PROVINCE.addChild(fac.createOMText(S_PROVINCE, sS_PROVINCE));
		BASEINFO.addChild(S_PROVINCE);

		OMElement S_SYSTEM = fac.createOMElement("S_SYSTEM", null);
		S_SYSTEM.addChild(fac.createOMText(S_SYSTEM, sS_SYSTEM));
		BASEINFO.addChild(S_SYSTEM);

		OMElement SERVICENAME = fac.createOMElement("SERVICENAME", null);
		SERVICENAME.addChild(fac.createOMText(SERVICENAME, sSERVICENAME));
		BASEINFO.addChild(SERVICENAME);

		OMElement T_PROVINCE = fac.createOMElement("T_PROVINCE", null);
		T_PROVINCE.addChild(fac.createOMText(T_PROVINCE, sT_PROVINCE));
		BASEINFO.addChild(T_PROVINCE);

		OMElement T_SYSTEM = fac.createOMElement("T_SYSTEM", null);
		T_SYSTEM.addChild(fac.createOMText(T_SYSTEM, sT_SYSTEM));
		BASEINFO.addChild(T_SYSTEM);

		OMElement Retry = fac.createOMElement("RETRY", null);
		Retry.addChild(fac.createOMText(Retry, sRETRY));
		BASEINFO.addChild(Retry);

		I_REQUEST.addChild(BASEINFO);

		OMElement MESSAGE = fac.createOMElement("MESSAGE", null);

		OMElement REMARK = fac.createOMElement("REMARK", null);
		REMARK.addChild(fac.createOMText(REMARK, "输入参数有误，message中是无效的xml数据"));
		MESSAGE.addChild(REMARK);

		OMElement RESULT = fac.createOMElement("RESULT", null);
		RESULT.addChild(fac.createOMText(RESULT, "1"));
		MESSAGE.addChild(RESULT);

		OMElement XMLDATA = fac.createOMElement("XMLDATA", null);
		XMLDATA.addChild(fac.createOMText(XMLDATA,
				"<data><fileUrl></fileUrl></data>"));
		MESSAGE.addChild(XMLDATA);

		I_REQUEST.addChild(MESSAGE);

		method.addChild(I_REQUEST);

		method.build();
		return method;
	}

	/* 
	 * 更新接口 --入口
	 * (non-Javadoc)
	 * @see com.tower.ggk.wsserver.IGGKService#GG_INTERFACE_Syn(java.lang.String)
	 */
	public String GG_INTERFACE_Syn(String info) {

		StringBuffer rtn = new StringBuffer();
		rtn.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		rtn.append("<ROOT>");

		String msgId = "0";
		// String info=XMLUtil.getbzXML(iinfo);
		//解析根节点下的基础信息 
		Document doc = XMLUtil.getDocumentByString(info);
		String system_source = doc.selectSingleNode("/ROOT/SYSTEM_SOURCE").getText();
		String system_target = doc.selectSingleNode("/ROOT/SYSTEM_TARGET").getText();
		String action = doc.selectSingleNode("/ROOT/ACTION").getText();
		List daNodes = doc.selectNodes("//ROOT/OBJECTS/OBJECT");
		rtn.append("<ACTION>").append(action).append("</ACTION >");
		rtn.append("<SYSTEM_SOURCE>").append(system_source).append("</SYSTEM_SOURCE>");
		rtn.append("<SYSTEM_TARGET>").append(system_source).append("</SYSTEM_TARGET>");

		
		try{
		//解析Object节点获取 Object对象	
		List<Map<String, Object>> invochgListPro=chgXMLObject(daNodes,info,system_source,system_target,action,msgId);
		//动作转换
		action=fixedTransAction(action,invochgListPro);
		//数据入库
		chgRKObject(Integer.valueOf(action),invochgListPro);
		//推送到消费系统
		chgSendXFObject(invochgListPro,info,system_source,action);
		}catch(Exception e){
			rtn.append("<ReturnCode>999</ReturnCode>");
			rtn.append("<ReturnMsg>").append(e.getMessage()).append("</ReturnMsg>");
			rtn.append("</ROOT>");
			return rtn.toString();
		}
    
		rtn.append("<ReturnCode>000</ReturnCode>");
		rtn.append("<ReturnMsg></ReturnMsg>");
		rtn.append("</ROOT>");

		// 执行推送任务 end
		return rtn.toString();
	}
	
	/**推送消费系统
	 * @param invochgListPro
	 * @param info
	 * @param system_source
	 * @param action
	 * @throws Exception
	 */
	public void chgSendXFObject(List ininvochgListPro,String info,String system_source,String action) throws Exception{
		List<Map<String,Object>> invochgListPro=ininvochgListPro;
		try{
			//调整invochgListPro 对象顺序 ，从小到大顺序处理
	        Collections.sort(invochgListPro, new Comparator<Map<String, Object>>() {
		            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		                int map1value = (Integer) o1.get("SEQ");
		                int map2value = (Integer) o2.get("SEQ");
		                return map1value - map2value;
		            }
		        });
	        
		for (int j = 0; j < invochgListPro.size(); j++) {
			String dataCode = updateRuleService.dataCodeTransRule(invochgListPro.get(j).get("DATACODE").toString());
		   // 执行推送任务 begin
			List<Map<String, String>> ListSysXF = ggkServiceDAO.getSystemByObjectCode(dataCode, "GG_INTERFACE_Syn",system_source);

			for (int i = 0; i < ListSysXF.size(); i++) {
				Map<String, String> systemMap = ListSysXF.get(i);
				String xfinfo =info;         //<SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE>
				       xfinfo = xfinfo.replace("<SYSTEM_SOURCE>" + system_source+"</SYSTEM_SOURCE>","<SYSTEM_SOURCE>H1-GGK</SYSTEM_SOURCE>");
				       xfinfo = xfinfo.replace("<SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET>","<SYSTEM_TARGET>"+systemMap.get("SYSTEM")+"</SYSTEM_TARGET>");
				SimpleDateFormat timemmsgid=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
	            String msgId=systemMap.get("SENDER")+timemmsgid.format(new java.util.Date());
				try {
					// String retrn=ggkClient.sendMethon(systemMap.get("ESBADDRESS"),systemMap.get("NAMESPACE"),systemMap.get("FUNCTION"),systemMap.get("SENDER"),systemMap.get("SERVCODE"), xfinfo);
					ggkServiceDAO.insertLog(xfinfo, dataCode, "H1-GGK",
							systemMap.get("SYSTEM"), action, "0", "推送成功，推送目标:"
									+ systemMap.get("SYSTEM"), msgId,"0");
				} catch (Exception e) {
					ggkServiceDAO.insertLog(xfinfo, dataCode, "H1-GGK",
							systemMap.get("SYSTEM"), action, "1", "推送失败，推送目标:"
									+ systemMap.get("SYSTEM"), msgId,"0");
				}
			}
			
		}
		}catch(Exception e){
			throw e;
		}
	}
	
	/**解析 XML 节点 获得Object 对象
	 * @param daNodes
	 * @param info
	 * @param system_source
	 * @param system_target
	 * @param action
	 * @param msgId
	 * @throws Exception
	 */
	public List chgXMLObject(List daNodes,String info,String system_source,String system_target,String action,String msgId) throws Exception{
		List<Map<String, Object>> invochgListPro = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < daNodes.size(); j++) {
			String keyField = "";
			String keyFieldValue = "";
			int objectSeq = 1;
			org.dom4j.Node daNode = (Node) daNodes.get(j);
			org.dom4j.Node daONode =daNode;// daNode.selectSingleNode("//OBJECT");
			String dataCode = daONode.selectSingleNode("DATA_CODE").getText();
			keyField = daONode.selectSingleNode("KEY_PROPERTY_ID").getText();
			keyFieldValue = daONode.selectSingleNode("KEY_PROPERTY_VALUE").getText();
			objectSeq = Integer.valueOf(daONode.selectSingleNode("OBJECT_SEQ").getText());
			boolean hasZr = false; // 是否具备字段责任
			ggkServiceDAO.insertLog(info, dataCode, system_source,system_target, action, "0", "接收成功", msgId,"0");
			Map<String, Object> daCodeMap = new HashMap<String, Object>();
			List<Map<String, String>> hasListPro = ggkServiceDAO.getPropertyByObjectCode(dataCode, null);
			List<Map<String, String>> chgListPro = new ArrayList<Map<String, String>>();
			
			//开启校验
			VerifyReturnVO verifyVo=updateRuleService.verifyAll(system_source, hasListPro, dataCode,keyField ,keyFieldValue , "3");
			if(verifyVo.getState()>0){
				// 异常抛出 begin
				throw new Exception(verifyVo.getMessage());
			}
			for (int i = 0; i < hasListPro.size(); i++) {
				Map<String, String> eProMap = hasListPro.get(i);
				String PROPERTY_CODE = eProMap.get("PROPERTY_CODE");
				if (system_source.equals(eProMap.get("SYSTEM_ZR"))) {
					hasZr = true;
				}
				boolean hasFieldZr = true; // 字段是否允许修改
				if (Integer.valueOf(action) == 2) {
					// 修改时只能修改责任系统对应的责任字段
					if (system_source.equals(eProMap.get("SYSTEM_ZR"))) {
						hasFieldZr = true;
					} else {
						hasFieldZr = false;
					}
				}
				String fix = "PROPERTY/" + PROPERTY_CODE;
				if (daONode.selectSingleNode(fix) != null && hasFieldZr) {
					eProMap.put("VALUE", daONode.selectSingleNode(fix)
							.getText());
					eProMap.put("NOVALUE", "false");
				} else {
					eProMap.put("NOVALUE", "true");
				}
				chgListPro.add(eProMap);
			}
			daCodeMap.put("SEQ", objectSeq);
			daCodeMap.put("PRO", chgListPro);
			daCodeMap.put("KEYFIELD", keyField);
			daCodeMap.put("KEYFIELDVALUE", keyFieldValue);
			daCodeMap.put("DATACODE", dataCode);
			invochgListPro.add(daCodeMap);
			/*// 异常抛出 begin
			if (!hasZr) {
				throw new Exception("非责任系统，不能新增、修改、删除公共库数据");
			} else if (keyFieldValue == null || keyFieldValue.equals("")) {
				throw new Exception("关键字段"+keyField+"必须有值");
			}
			// 异常抛出 end
*/
		}
		return invochgListPro;
	}
	
	
	/**执行数据入库
	 * @param iaction
	 * @param invochgListPro
	 * @throws Exception 
	 */
	public void chgRKObject(int iaction,List ininvochgListPro) throws Exception{
		String dataCode="";
		List<Map<String,Object>> invochgListPro=ininvochgListPro;
		try{
			//调整invochgListPro 对象顺序 ，从小到大顺序处理
	        Collections.sort(invochgListPro, new Comparator<Map<String, Object>>() {
		            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
		                int map1value = (Integer) o1.get("SEQ");
		                int map2value = (Integer) o2.get("SEQ");
		                return map1value - map2value;
		            }
		        });
	        
		for (int j = 0; j < invochgListPro.size(); j++) {
			dataCode = invochgListPro.get(j).get("DATACODE").toString();
			List<Map<String, String>> chgListPro = (List<Map<String, String>>) invochgListPro.get(j).get("PRO");
			String keyField = invochgListPro.get(j).get("KEYFIELD").toString();
			String keyFieldValue = invochgListPro.get(j).get("KEYFIELDVALUE").toString();
			
			//财务系统卡片 无修改动作，需要判断存在转换动作类型 新增转为修改
			if(iaction==1&&"PUB_FIXED_ASSETS".equals(dataCode)&&updateRuleService.isExistsObject(dataCode,keyField,keyFieldValue)){
				iaction=2;
			}
			
			// 执行 公共库更新操作 begin
			switch (iaction) {
			case 1:
				createObject(dataCode, chgListPro, keyField, keyFieldValue);
				break;
			case 2:
				modifyObject(dataCode, chgListPro, keyField,
						keyFieldValue);
				break;
			case 3:
				boolean lee=deleteObject(dataCode, keyField, keyFieldValue);
				if(lee){
					throw new Exception("没有找到可删除的数据，删除数量为0");
				}
				break;

			default:
				break;

			}
			// 执行 公共库更新操作 end
			
		}
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * 新增对象
	 * @param dataCode
	 * @param chgListPro
	 * @param indaoSql
	 * @return
	 */
	private boolean createObject(String dataCode,List inchgListPro,String keyField,String keyFielValue) throws Exception{
		boolean ob = false;

		List<Map<String, String>> chgListPro=inchgListPro;
		StringBuffer daoSql = new StringBuffer();
		if(updateRuleService.isExistsObject(dataCode,keyField,keyFielValue)){
			throw new Exception(dataCode+"["+keyField+":"+keyFielValue+"]已经存在,不能插入重复数据");
		}
		
		// 新增
		daoSql.setLength(0);
		daoSql.append("INSERT INTO ").append(dataCode).append(" (");
		StringBuffer endsql = new StringBuffer();
		int chgi = 0;
		for (int i = 0; i < chgListPro.size(); i++) {
			Map<String, String> eProMap = chgListPro.get(i);
			String PROPERTY_CODE = eProMap.get("PROPERTY_CODE");
			String NOVALUE = eProMap.get("NOVALUE");
			if ("false".equals(NOVALUE)) {
				if (chgi == 0) {
					daoSql.append(" ").append(PROPERTY_CODE);
					endsql.append("'").append(eProMap.get("VALUE")).append("'");
				} else {
					daoSql.append(",").append(PROPERTY_CODE);
					endsql.append(",").append("'").append(eProMap.get("VALUE")).append("'");
				}
				chgi++;
			}
		}
		daoSql.append(" ) values( ").append(endsql).append(")");
		ggkServiceDAO.excuteSql(daoSql.toString());
		ob=true;
		return ob;
	}
	
	
	/**
	 * 修改对象
	 * @param dataCode
	 * @param chgListPro
	 * @param indaoSql
	 * @return
	 */
	private boolean modifyObject(String indataCode,List inchgListPro,String keyField,String keyFielValue)  throws Exception{
		boolean ob = false;

		List<Map<String, String>> chgListPro=inchgListPro;
		StringBuffer daoSql = new StringBuffer();
		String dataCode=updateRuleService.dataCodeTransRule(indataCode);
		// 修改
		daoSql.append("UPDATE ").append(dataCode).append(" set ");
		int chgi = 0;
		for (int i = 0; i < chgListPro.size(); i++) {
			Map<String, String> eProMap = chgListPro.get(i);
			String PROPERTY_CODE = eProMap.get("PROPERTY_CODE");
			String NOVALUE = eProMap.get("NOVALUE");
			if ("false".equals(NOVALUE)) {
				if (chgi == 0) {
					daoSql.append(" ").append(PROPERTY_CODE).append("='").append(eProMap.get("VALUE")).append("'");
				} else {
					daoSql.append(" ,").append(PROPERTY_CODE).append("='").append(eProMap.get("VALUE")).append("'");
				}
				chgi++;
			}
		}
		daoSql.append(" where ").append(keyField).append("='").append(keyFielValue).append("'");
		ggkServiceDAO.excuteSql(daoSql.toString());
		ob=true;
		return ob;
	}
	
	/**
	 * 删除对象
	 * @param dataCode
	 * @param chgListPro
	 * @param indaoSql
	 * @return
	 */
	private boolean deleteObject(String dataCode,String keyField,String keyFielValue)  throws Exception{
		boolean ob = false;

		StringBuffer daoSql = new StringBuffer();
		// 删除
		daoSql.append("DELETE FROM ").append(dataCode).append("  ");
		daoSql.append(" where ").append(keyField).append("='").append(keyFielValue).append("'");
		int ci=ggkServiceDAO.excuteSql(daoSql.toString());
		if(ci>0){
		 ob=true;
		}
		return ob;
	}
	
	
	
	
	/**
	 * 固定资产 新增转修改动作
	 * @param action
	 * @param invochgListPro
	 * @return
	 */
	private String fixedTransAction(String iaction,List invochgListPro){
		String action=iaction;
		for(int i=0;i<invochgListPro.size()&&i<1;i++){
			invochgListPro.get(i);
			String dataCode = ((Map<String,Object>)invochgListPro.get(i)).get("DATACODE").toString();
			String keyField = ((Map<String,Object>)invochgListPro.get(i)).get("KEYFIELD").toString();
			String keyFieldValue = ((Map<String,Object>)invochgListPro.get(i)).get("KEYFIELDVALUE").toString();
			//财务系统卡片 无修改动作，需要判断存在转换动作类型 新增转为修改
			if("1".equals(action)&&"PUB_FIXED_ASSETS".equals(dataCode)&&updateRuleService.isExistsObject(dataCode,keyField,keyFieldValue)){
				action="2";
			}
		}
		return action;
	}

} 