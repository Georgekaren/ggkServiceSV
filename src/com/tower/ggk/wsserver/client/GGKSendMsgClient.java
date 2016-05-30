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
public class GGKSendMsgClient {

	//private static String esbAddress = "http://localhost:9080/axis2server/services/ggkService";
	private static String esbAddress = "http://123.126.34.135:9000/proxy";
    private static String namespace="http://impl.wsserver.ggk.tower.com";
	private static String function="GG_INTERFACE_Syn";
    //ESB 注册信息 提供接口URL地址 给ESB后 从ESB取 这两个参数
	private static String v_Sender="30.3005";
	private static String v_ServCode="10.1001.GG_INTERFACE_Syn";
	
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
               Sender.setValue(in_Sender);
               SOAPElement Time= Route.addChildElement("Time"); 
               SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); 
               Time.setValue(time.format(new java.util.Date()));
               SOAPElement ServCode= Route.addChildElement("ServCode"); 
               ServCode.setValue(in_ServCode);
               SOAPElement MsgId= Route.addChildElement("MsgId"); 
               SimpleDateFormat timemmsgid=new SimpleDateFormat("yyyyMMddHHmmssSSS"); 
               MsgId.setValue(in_Sender+timemmsgid.format(new java.util.Date()));
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
		
		String modifyxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>2</ACTION><DATA_CODE>STATION</DATA_CODE><SYSTEM>H1-ZY</SYSTEM><PROPERTY><CODE>1</CODE><CHINA_NAME>21</CHINA_NAME><DISTRICT_ID>31</DISTRICT_ID><PROPERTY_ID>41</PROPERTY_ID><REGION_ID>5</REGION_ID><COVER_SCENE>6</COVER_SCENE><RES_TYPE_ID>7</RES_TYPE_ID><CREATE_TIME>8</CREATE_TIME></PROPERTY></ROOT>";
		String delxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><ROOT><ACTION>3</ACTION><DATA_CODE>STATION</DATA_CODE><SYSTEM>H1-ZY</SYSTEM><PROPERTY><CODE>1</CODE></PROPERTY></ROOT>";
		
		String rtn=GGKSendMsgClient.sendMethon(function, insertxml);
	 
		System.out.println(rtn);
	}
	
	
}
