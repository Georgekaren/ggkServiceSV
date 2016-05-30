package com.tower.ggk.wsserver.service;

import java.util.List;
import java.util.Map;

import com.tower.ggk.wsserver.dao.GGKServiceDAO;
import com.tower.ggk.wsserver.utils.VerifyReturnVO;

/**更新规则配置
 * @author shen.zhi
 *  2016-5-27 
 */
public class UpdateRuleService {

	
	private GGKServiceDAO ggkServiceDAO;
	public GGKServiceDAO getGgkServiceDAO() {
		return ggkServiceDAO;
	}

	public void setGgkServiceDAO(GGKServiceDAO ggkServiceDAO) {
		this.ggkServiceDAO = ggkServiceDAO;
	}
	
	/**校验规则集合入口
	 * @param dataCode
	 * @param keyField
	 * @param verifyType 1接口规则校验 2 业务规则校验 3两项都检测
	 * @param keyFielValue
	 * @return VerifyReturnVO
	 */
	public VerifyReturnVO verifyAll(String system_source,List hasListPro,String dataCode,String keyField,String keyFielValue,String verifyType){
		VerifyReturnVO rtnveriy;
		rtnveriy=verifyInterfaceAll(system_source,hasListPro);
		if(rtnveriy.getState()>0){
			return rtnveriy;
		}
		rtnveriy=verifyBusinessAll(dataCode,keyField,keyFielValue);
		return rtnveriy;
	}
	
	
	/**接口规则校验 
	 * @param system_source
	 * @param hasListPro
	 * @return true 通过 false 不通过
	 */
	public VerifyReturnVO verifyInterfaceAll(String system_source,List hasListPro){
		VerifyReturnVO rtnveriy=new VerifyReturnVO(0);
		boolean bhas=false;
		for (int i = 0; i < hasListPro.size(); i++) {
			Map<String, String> eProMap = (Map<String, String>) hasListPro.get(i);
			if (system_source.equals(eProMap.get("SYSTEM_ZR"))) {
				bhas=true;
			}
		}
		if(!bhas){
			rtnveriy=new VerifyReturnVO(1,"ERRO_GGK_INTERFACE_00001");
		}
		return rtnveriy;
	}
	
	/**业务规则校验
	 * @param dataCode
	 * @param keyField
	 * @param keyFielValue
	 * @return
	 */
	public VerifyReturnVO verifyBusinessAll(String dataCode,String keyField,String keyFielValue){
		VerifyReturnVO rtnveriy=new VerifyReturnVO(0);
		//关键字段判断
		if(isNull(keyField)||isNull(keyFielValue)){
			rtnveriy.setCode("ERRO_GGK_INTERFACE_00002");
			rtnveriy.setState(1);
			rtnveriy.setMessage(rtnveriy.getProperyValue("ERRO_GGK_INTERFACE_00002").replace("{0}", keyField));
		}
		//唯一性判断
		else if(!"PUB_FIXED_ASSETS".equals(dataCode)&&isExistsObject(dataCode,keyField,keyFielValue)){
			rtnveriy=new VerifyReturnVO(2,"ERRO_GGK_INTERFACE_00003");
		}
		
		return rtnveriy;
	}
	
	
	/**
	 * 校验对象是否存在对象
	 * @param dataCode
	 * @param keyField
	 * @param keyFielValue
	 * @return
	 */
	public boolean isExistsObject(String dataCode,String keyField,String keyFielValue){
		boolean ob = false;
		StringBuffer daoSql = new StringBuffer();
		daoSql.append("select count(1) from  ").append(dataCode);
		daoSql.append(" where ").append(keyField).append("='").append(keyFielValue).append("'");
		if(ggkServiceDAO.getCountBySql(daoSql.toString())>0){
			return true;
		}
		return ob;
	}
	
	/**
	 * @param dataCode 转换规则
	 * @return String 
	 */
	public String dataCodeTransRule(String dataCode){
		String rtndataCode=dataCode;
		if("SPC_RADIO_STATION_ SITE_FEE".equals(rtndataCode)
				||"SPC_RADIO_STATION_ ELEC_CHARGE".equals(rtndataCode)
				||"SPC_RADIO_STATION_ SHARE_UNIT".equals(rtndataCode)
				||"SPC_RADIO_STATION_ CONNECT_NETWORK_DATE".equals(rtndataCode)){
			rtndataCode="SPC_RADIO_STATION";
			}else if("PUB_FIXED_ASSETS_VALUE".equals(rtndataCode)||"ITF_STATION_OPERATE_DATA".equals(rtndataCode)){
				rtndataCode="PUB_FIXED_ASSETS";
			}
		
		return  rtndataCode;
	}
	
	/**非空判断
	 * @return true null,false is not null;
	 */
	public boolean isNull(String param){
		if(param==null||param.equals("")){
			return true;
		}
		return false;
	}
	
}
