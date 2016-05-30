package com.tower.ggk.wsserver; 

import org.apache.axiom.om.OMElement;

public interface IGGKService{ 

	//自定义Webservice，用于兼容 对象格式入参。 接口范例   入参名：MT_CPMIS2OA_Req  回参名：MT_CPMIS2OA_Resp
	public OMElement MT_CPMIS2OA_Req(OMElement soapBody);
	//站址新增接收接口
	public String GG_INTERFACE_Syn(String info);
}