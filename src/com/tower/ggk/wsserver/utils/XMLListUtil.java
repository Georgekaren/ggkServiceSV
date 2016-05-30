package com.tower.ggk.wsserver.utils;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class XMLListUtil {

	
	public static String stripNonValidXMLChars(String str) {
		  if (str == null || "".equals(str)) {
		    return str;
		  }
		  return str.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
		}
	public static void main(String args[]) throws IOException 
	{ 
	try { 
	String inxml="<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><DEMAND_TYPE><![CDATA[08]]></DEMAND_TYPE><REQUEST_ID>1016051301758166</REQUEST_ID></root>";
	
	org.dom4j.io.SAXReader saxReader = new SAXReader();
	Document doc =saxReader.read(new   ByteArrayInputStream(inxml.getBytes()));
	String xpath ="root/DEMAND_TYPE";
	System.out.print(doc.selectSingleNode(xpath).getText());  
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	}  
}
