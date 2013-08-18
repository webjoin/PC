package com.pc.pconsumption.framework;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.pc.pconsumption.po.ConsumptionPO;
import com.pc.pconsumption.util.ConfigKeys;
import com.pc.pconsumption.util.DBHelper;

public class SynchroDataXMLDB implements Runnable{
	
	private static transient Logger log = Logger.getLogger(SynchroDataXMLDB.class.getName());
	
	public void run() {
		Thread curr = Thread.currentThread();
		try {
			Thread.sleep(1000);
			
			log.info(curr.getName()+" 醒了！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		获取数据表 和 xml 中 当月的数量是否一致 
	}
	
	public static void main(String[] args) {
		XmlProcessHandler handler = new XmlProcessHandler();
		String xmlPath = DBHelper.class.getResource(ConfigKeys.XmlClazz.XML_PATH).getPath();
		try {
			FileInputStream fis = new FileInputStream(xmlPath);
			handler.getConsumptionPOs(fis);
		} catch (Exception e) {
			log.info("获取同步文件"+xmlPath+"出现异常");
			e.printStackTrace();
		}
	}
	
	
	
}
class XmlProcessHandler extends DefaultHandler{
	private List<ConsumptionPO> pos = null;
	private ConsumptionPO po = null;
	public List<ConsumptionPO> getConsumptionPOs(InputStream xmlStream) throws Exception{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		parser.parse(xmlStream, this);
		return this.getConsumptionPOs();
	}
	public List<ConsumptionPO> getConsumptionPOs(){
		return pos;
	}
	
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	public void endElement(String uri, String localName, String qName)throws SAXException {
		if("".equals(qName)){
			pos.add(po);
			po = null;
		}
	}

	
	public void startDocument() throws SAXException {
		pos = new ArrayList<ConsumptionPO>();
	}

	
	public void startElement(String uri, String localName, String qName,Attributes attrs) throws SAXException {
		if("".equals("qName"));{
			po = new ConsumptionPO();
			po.setId(Long.parseLong(attrs.getValue(0)));
		}
	}
}
