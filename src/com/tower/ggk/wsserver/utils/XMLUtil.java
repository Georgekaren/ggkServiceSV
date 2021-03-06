package com.tower.ggk.wsserver.utils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.InputSource;

public class XMLUtil {

	public static Map xml2map(String xmlString) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		Element rootElement = doc.getRootElement();
		Map<String, Object> map = new HashMap<String, Object>();
		ele2map(map, rootElement);
		//System.out.println(map);
		// 到此xml2map完成，下面的代码是将map转成了json用来观察我们的xml2map转换的是否ok
		//String string = JSONObject.fromObject(map).toString();
		//System.out.println(string);
		return map;
	}
	
	/***
	 * 核心方法，里面有递归调用
	 * 
	 * @param map
	 * @param ele
	 */
	public static void ele2map(Map map, Element ele) {
		System.out.println(ele);
		// 获得当前节点的子节点
		List<Element> elements = ele.elements();
		if (elements.size() == 0) {
			// 没有子节点说明当前节点是叶子节点，直接取值即可
			map.put(ele.getName(), ele.getText());
		} else if (elements.size() == 1) {
			// 只有一个子节点说明不用考虑list的情况，直接继续递归即可
			Map<String, Object> tempMap = new HashMap<String, Object>();
			ele2map(tempMap, elements.get(0));
			map.put(ele.getName(), tempMap);
		} else {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Element element : elements) {
				tempMap.put(element.getName(), null);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				List<Element> elements2 = ele.elements(new QName(string,
						namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Map> list = new ArrayList<Map>();
					for (Element element : elements2) {
						Map<String, Object> tempMap1 = new HashMap<String, Object>();
						ele2map(tempMap1, element);
						list.add(tempMap1);
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					Map<String, Object> tempMap1 = new HashMap<String, Object>();
					ele2map(tempMap1, elements2.get(0));
					map.put(string, tempMap1);
				}
			}
		}
	}
	
	public static String formatXml(Document document, String charset, boolean istrans) { 
	     OutputFormat format = OutputFormat.createPrettyPrint(); 
	     format.setEncoding(charset); 
	     StringWriter sw = new StringWriter(); 
	     XMLWriter xw = new XMLWriter(sw, format); 
	     xw.setEscapeText(istrans); 
	     try { 
	             xw.write(document); 
	             xw.flush(); 
	             xw.close(); 
	     } catch (IOException e) { 
	             System.out.println("格式化XML文档发生异常，请检查！"); 
	             e.printStackTrace(); 
	     } 
	     return sw.toString(); 
	}
	
	public static Document getDocumentByString(String inxml){
		String getxml=inxml;
		org.dom4j.io.SAXReader saxReader = new SAXReader();
		getxml=getxml.replace("\n", "").replace("\r", "").replace(" ", "").replace("<![CDATA[", "").replace("]]>", "").replace("xmlversion=\"1.0\"encoding", "xml version=\"1.0\" encoding");
		Document doc = null;
		try {
			doc = saxReader.read(new   ByteArrayInputStream(getxml.getBytes("UTF-8")));
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public static String getbzXML(String inxml){
		String getxml=inxml;
		getxml=getxml.replace("\n", "").replace("\r", "").replace(" ", "").replace("<![CDATA[", "").replace("]]>", "").replace("xmlversion=\"1.0\"encoding", "xml version=\"1.0\" encoding");
		return getxml;
	}
	
	
	
}
