package com.tower.ggk.wsserver.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPHeaderElement;
public class TGGKSendMsgClient {

	//http://123.126.34.135:9000/proxy ESB 地址
	private static String esbAddress = "http://localhost:9080/ggkServer/services/ggkService";
	//private static String esbAddress = "http://123.126.34.92:7011/ggkServer/services/ggkService";
//	private static String esbAddress = "http://123.126.34.135:9000/proxy";
	   
	private static String namespace="http://impl.wsserver.ggk.tower.com";
	private static String function="GG_INTERFACE_Syn";
    //ESB 注册信息 提供接口URL地址 给ESB后 从ESB取 这两个参数
	private static String v_Sender="60.6002";
	private static String v_ServCode="60.6002.GG_INTERFACE_Syn";
	
    public static String sendMethon(String functionName,String inxml){
    	String returnStr ="";
    	try {
			//System.out.println("==============执行中============================");
			long b = System.currentTimeMillis();
			Service ser = new Service();
			Call call = (Call) ser.createCall();
			
            call.setSOAPActionURI(namespace + functionName);//要调用方法的url
            call.setOperationName(new QName(namespace, functionName));// 设置操作的名称。
			
          //设置头信息
            SOAPHeaderElement EsbHeaderElement = new SOAPHeaderElement(namespace, "Esb");   
            EsbHeaderElement.setNamespaceURI(namespace);
            try{  
               SOAPElement Route= EsbHeaderElement.addChildElement("Route"); 
               
               SOAPElement Sender= Route.addChildElement("Sender"); 
               Sender.setValue(v_Sender);
               SOAPElement Time= Route.addChildElement("Time"); 
               SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
               Time.setValue(time.format(new java.util.Date()));
               SOAPElement ServCode= Route.addChildElement("ServCode"); 
               ServCode.setValue(v_ServCode);
               SOAPElement MsgId= Route.addChildElement("MsgId"); 
               SimpleDateFormat timemmsgid=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
               MsgId.setValue(v_Sender+timemmsgid.format(new java.util.Date()));
               SOAPElement AuthCode= Route.addChildElement("AuthCode"); 
              
               SOAPElement TransId= Route.addChildElement("TransId");
               
               call.setTargetEndpointAddress(new java.net.URL(esbAddress));  
            }catch (SOAPException e){
                e.printStackTrace();
            } catch (MalformedURLException e) {
				e.printStackTrace();
			}
            call.addHeader(EsbHeaderElement);
            call.addParameter("info", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);// 返回的数据类型 
			call.setTargetEndpointAddress(esbAddress);
			returnStr= (String) call.invoke(new Object[] { inxml });
			//long e = System.currentTimeMillis();
			//System.out.println("<>" + returnStr);
			//System.out.println("Time:" + (e - b));
		} catch (ServiceException se) {
			se.printStackTrace();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
    	return returnStr;
    }
    
    public static String sendMethon(String esbAddress,String namespace,String functionName,String in_Sender,String in_ServCode,String inxml){
    	String returnStr ="";
    	try {
			//System.out.println("==============执行中============================");
			long b = System.currentTimeMillis();
			Service ser = new Service();
			Call call = (Call) ser.createCall();
			
            call.setSOAPActionURI(namespace + functionName);//要调用方法的url
            call.setOperationName(new QName(namespace, functionName));// 设置操作的名称。
			
            //设置头信息
            SOAPHeaderElement EsbHeaderElement = new SOAPHeaderElement(namespace, "Esb");   
            EsbHeaderElement.setNamespaceURI(namespace);
            try{  
               SOAPElement Route= EsbHeaderElement.addChildElement("Route"); 
               
               SOAPElement Sender= Route.addChildElement("Sender"); 
               Sender.setValue(v_Sender);
               SOAPElement Time= Route.addChildElement("Time"); 
               SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
               Time.setValue(time.format(new java.util.Date()));
               SOAPElement ServCode= Route.addChildElement("ServCode"); 
               ServCode.setValue(v_ServCode);
               SOAPElement MsgId= Route.addChildElement("MsgId"); 
               SimpleDateFormat timemmsgid=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
               MsgId.setValue(v_Sender+timemmsgid.format(new java.util.Date()));
               SOAPElement AuthCode= Route.addChildElement("AuthCode"); 
              
               SOAPElement TransId= Route.addChildElement("TransId");
               
               call.setTargetEndpointAddress(new java.net.URL(esbAddress));  
            }catch (SOAPException e){
                e.printStackTrace();
            } catch (MalformedURLException e) {
				e.printStackTrace();
			}
            call.addHeader(EsbHeaderElement);
            call.addParameter("info", XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);// 返回的数据类型 
			call.setTargetEndpointAddress(esbAddress);
			System.out.println("<>" + inxml);
			returnStr= (String) call.invoke(new Object[] { inxml });
			//long e = System.currentTimeMillis();
			//System.out.println("<>" + returnStr);
			//System.out.println("Time:" + (e - b));
		} catch (ServiceException se) {
			se.printStackTrace();
		} catch (RemoteException re) {
			re.printStackTrace();
		}
    	return returnStr;
    }
	public static void main(String[] arg) {
		String cdatabegin="<![CDATA[";
		String cdataend="]]>";
		String insertxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><DATA_CODE>STATION</DATA_CODE><SYSTEM>H1-ZY</SYSTEM><PROPERTY><CODE>1</CODE><CHINA_NAME>21</CHINA_NAME><DISTRICT_ID>31</DISTRICT_ID><PROPERTY_ID>41</PROPERTY_ID><REGION_ID>5</REGION_ID><COVER_SCENE>6</COVER_SCENE><RES_TYPE_ID>7</RES_TYPE_ID><CREATE_TIME>8</CREATE_TIME></PROPERTY></ROOT>";
		insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>0000000000010000000011</KEY_PROPERTY_VALUE><PROPERTY><STATION_ID>000102010000000000000653</STATION_ID><STATION_NO>SH.SX.YP-BLBJGWZX</STATION_NO><CHINA_NAME>百联滨江购物中心</CHINA_NAME><CODE>31011000000001</CODE><TYPE_ID>2010036</TYPE_ID><DISTRICT_ID>310109</DISTRICT_ID><REGION_ID>000102000000000000310110</REGION_ID><SHARE_UNIT>88431003</SHARE_UNIT><GRADE_ID>2051706</GRADE_ID><LOCATION>杨浦区平凉路1399号</LOCATION><COVER_SCENE>2010936</COVER_SCENE><PROP_CHAR_ID>2010401</PROP_CHAR_ID><LONGITUDE>121.5322220000</LONGITUDE><LATITUDE>31.2666730000</LATITUDE><MNT_STATE_ID>160054</MNT_STATE_ID><RES_TYPE_ID>201</RES_TYPE_ID><IS_SHARE>1</IS_SHARE><SITE_FEE>1001.12</SITE_FEE><ELEC_CHARGE>1000.20</ELEC_CHARGE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		
		
		insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000102010000000000000653</KEY_PROPERTY_VALUE><PROPERTY><STATION_ID>000102010000000000000653</STATION_ID><STATION_NO>SH.SX.YP-BLBJGWZX</STATION_NO><CHINA_NAME>百联滨江购物中心</CHINA_NAME><CODE>31011000000001</CODE><TYPE_ID>2010036</TYPE_ID><DISTRICT_ID>310109</DISTRICT_ID><REGION_ID>000102000000000000310110</REGION_ID><SHARE_UNIT>88431003</SHARE_UNIT><GRADE_ID>2051706</GRADE_ID><LOCATION>杨浦区平凉路1399号</LOCATION><COVER_SCENE>2010936</COVER_SCENE><PROP_CHAR_ID>2010401</PROP_CHAR_ID><LONGITUDE>121.5322220000</LONGITUDE><LATITUDE>31.2666730000</LATITUDE><MNT_STATE_ID>160054</MNT_STATE_ID><RES_TYPE_ID>201</RES_TYPE_ID><IS_SHARE>1</IS_SHARE><SITE_FEE>1001.12</SITE_FEE><ELEC_CHARGE>1000.20</ELEC_CHARGE></PROPERTY></OBJECT><OBJECT><OBJECT_SEQ>2</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000102010000000000000654</KEY_PROPERTY_VALUE><PROPERTY><STATION_ID>000102010000000000000654</STATION_ID><STATION_NO>SH.SX.YP-BLBJGWZX</STATION_NO><CHINA_NAME>百联滨江购物中心</CHINA_NAME><CODE>31011000000001</CODE><TYPE_ID>2010036</TYPE_ID><DISTRICT_ID>310109</DISTRICT_ID><REGION_ID>000102000000000000310110</REGION_ID><SHARE_UNIT>88431003</SHARE_UNIT><GRADE_ID>2051706</GRADE_ID><LOCATION>杨浦区平凉路1399号</LOCATION><COVER_SCENE>2010936</COVER_SCENE><PROP_CHAR_ID>2010401</PROP_CHAR_ID><LONGITUDE>121.5322220000</LONGITUDE><LATITUDE>31.2666730000</LATITUDE><MNT_STATE_ID>160054</MNT_STATE_ID><RES_TYPE_ID>201</RES_TYPE_ID><IS_SHARE>1</IS_SHARE><SITE_FEE>1001.12</SITE_FEE><ELEC_CHARGE>1000.20</ELEC_CHARGE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		//站址
		insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000102010000000000000653</KEY_PROPERTY_VALUE><PROPERTY><STATION_ID>000102010000000000000653</STATION_ID><STATION_NO>SH.SX.YP-BLBJGWZX</STATION_NO><CHINA_NAME>百联滨江购物中心</CHINA_NAME><CODE>31011000000001</CODE><TYPE_ID>2010036</TYPE_ID><DISTRICT_ID>310109</DISTRICT_ID><REGION_ID>000102000000000000310110</REGION_ID><SHARE_UNIT>88431003</SHARE_UNIT><GRADE_ID>2051706</GRADE_ID><LOCATION>杨浦区平凉路1399号</LOCATION><COVER_SCENE>2010936</COVER_SCENE><PROP_CHAR_ID>2010401</PROP_CHAR_ID><LONGITUDE>121.5322220000</LONGITUDE><LATITUDE>31.2666730000</LATITUDE><MNT_STATE_ID>160054</MNT_STATE_ID><RES_TYPE_ID>201</RES_TYPE_ID><IS_SHARE>1</IS_SHARE><SITE_FEE>1001.12</SITE_FEE><ELEC_CHARGE>1000.20</ELEC_CHARGE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		
		//财务卡片 
		//insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-CW</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>PUB_FIXED_ASSETS</DATA_CODE><KEY_PROPERTY_ID>ASSETS_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>62362553</KEY_PROPERTY_VALUE><PROPERTY><ASSETS_ID>62362553</ASSETS_ID><ASSETS_NO>370400108295</ASSETS_NO><VALUE>45109.8800</VALUE><STATION_ID>003702010200000022922475</STATION_ID></PROPERTY></OBJECT></OBJECTS></ROOT>";
		//铁塔
		//insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>TW_TOWER</DATA_CODE><KEY_PROPERTY_ID>TOWER_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000188430000000000000506</KEY_PROPERTY_VALUE><PROPERTY><TOWER_NAME>安康市汉滨区迎风乡红霞村/三管塔-001</TOWER_NAME><TOWER_CODE>SD.LC.SX-WLHYN/JGU-01</TOWER_CODE><TOWER_ID>000188430000000000000506</TOWER_ID><STATION_ID>000102010000000001105945</STATION_ID><TYPE_ID>88430202</TYPE_ID><REGION_ID>000102000000000000610902</REGION_ID><HEIGHT>40.000</HEIGHT><MODEL_ID>88430001</MODEL_ID><LONGITUDE>108.8852450000</LONGITUDE><LATITUDE>32.6097560000</LATITUDE><ELEVATION></ELEVATION><MANUFACTOR_ID></MANUFACTOR_ID><WIDTH>2.500</WIDTH><PROP_CHAR_ID>2010401</PROP_CHAR_ID><RESOURCE_FROM>1</RESOURCE_FROM><IS_JOINT_BUILDED></IS_JOINT_BUILDED><WIND_PRESSURE>0.45</WIND_PRESSURE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		///机房
		//insertxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>1</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_ROOM</DATA_CODE><KEY_PROPERTY_ID>ROOM_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>004202050200000039506980</KEY_PROPERTY_VALUE><PROPERTY><ROOM_ID>004202050200000039506980</ROOM_ID><ROOM_NO>HE.ZX.XT-XTGD/1/YTG1</ROOM_NO><CHINA_NAME>仙桃广电/机房1</CHINA_NAME><CODE></CODE><STATION_ID>004202010200000024120788</STATION_ID><TYPE_ID>2050063</TYPE_ID><IS_SHARE>1</IS_SHARE><SHARE_UNIT>80001</SHARE_UNIT><ROOM_ID>004202050200000039506980</ROOM_ID><PROP_CHAR_ID>2013002</PROP_CHAR_ID><POS_X>113.4520510000</POS_X><POS_Y>30.3642010000</POS_Y><PROJECT_ID>2013002</PROJECT_ID></PROPERTY></OBJECT></OBJECTS></ROOT>";
					
		
		String modifyxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><DATA_CODE>STATION</DATA_CODE><SYSTEM>H1-ZY</SYSTEM><PROPERTY><CODE>1</CODE><CHINA_NAME>21</CHINA_NAME><DISTRICT_ID>31</DISTRICT_ID><PROPERTY_ID>41</PROPERTY_ID><REGION_ID>5</REGION_ID><COVER_SCENE>6</COVER_SCENE><RES_TYPE_ID>7</RES_TYPE_ID><CREATE_TIME>8</CREATE_TIME></PROPERTY></ROOT>";
		//修改站址电费
		modifyxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000102010000000000000653</KEY_PROPERTY_VALUE><PROPERTY><ELEC_CHARGE>1111.20</ELEC_CHARGE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		//修改财务卡片 
		modifyxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><SYSTEM_SOURCE>H1-CW</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>PUB_FIXED_ASSETS</DATA_CODE><KEY_PROPERTY_ID>ASSETS_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>62362553</KEY_PROPERTY_VALUE><PROPERTY><ASSETS_ID>62362553</ASSETS_ID><ASSETS_NO>370400108295</ASSETS_NO><VALUE>6109.8800</VALUE><STATION_ID>003702010200000022922475</STATION_ID></PROPERTY></OBJECT></OBJECTS></ROOT>";
		//修改铁塔
		modifyxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>TW_TOWER</DATA_CODE><KEY_PROPERTY_ID>TOWER_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000188430000000000000506</KEY_PROPERTY_VALUE><PROPERTY><TOWER_NAME>安康市汉滨区迎风乡红霞村/三管塔-001-3</TOWER_NAME><TYPE_ID>88430202</TYPE_ID><WIND_PRESSURE>0.49</WIND_PRESSURE></PROPERTY></OBJECT></OBJECTS></ROOT>";
		//机房
		modifyxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_ROOM</DATA_CODE><KEY_PROPERTY_ID>ROOM_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>004202050200000039506980</KEY_PROPERTY_VALUE><PROPERTY><CHINA_NAME>仙桃广电/机房1-S</CHINA_NAME></PROPERTY></OBJECT></OBJECTS></ROOT>";
					
		String delxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>3</ACTION><DATA_CODE>STATION</DATA_CODE><SYSTEM>H1-ZY</SYSTEM><PROPERTY><CODE>1</CODE></PROPERTY></ROOT>";
		
		delxml="<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>3</ACTION><SYSTEM_SOURCE>H1-ZY</SYSTEM_SOURCE><SYSTEM_TARGET>H1-GGK</SYSTEM_TARGET><OBJECTS><OBJECT><OBJECT_SEQ>1</OBJECT_SEQ><DATA_CODE>SPC_RADIO_STATION</DATA_CODE><KEY_PROPERTY_ID>STATION_ID</KEY_PROPERTY_ID><KEY_PROPERTY_VALUE>000102010000000000000653</KEY_PROPERTY_VALUE><PROPERTY></PROPERTY></OBJECT></OBJECTS></ROOT>";
		
		
		String rtn=TGGKSendMsgClient.sendMethon(function, insertxml);
	 
		System.out.println(rtn);
	}
	
	
}
