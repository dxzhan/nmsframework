package com.nms.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nms.message.result.CResult;
import com.nms.message.result.MessageResult;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CResultTest
{
	static Logger logger = LoggerFactory.getLogger(CResultTest.class);

	@BeforeClass
	public static void Up()
	{
		logger.info("before.....");
	}

	@AfterClass
	public static void down()
	{
		logger.info("after....");
	}

	@Test
	public void toXml()
	{
		CResult<String> result = new CResult<String>(OpCode.SUCCESS.toString(),1,"ttttttt","ssssssssssssssss","sam");
		result.success("1","hello world");
		result.addMsg("dddsf dsfs");
		result.addMsg("lkajl21432@@#%$$#&^^%*^)");

		String jsonText = result.toJson();
		String xmlText = "xml text";

		try {
			xmlText = result.toXml();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		logger.info("CResult json:"+jsonText);
		logger.info("CResult xml:"+xmlText);

		CResult<String> xmlResult = new CResult<String>();

		try {
			logger.info("CResult from Xml to json");
			xmlResult = xmlResult.fromXml(xmlText);
			logger.info(xmlResult.toJson());
			assertEquals("Xml to Json not equals",result,xmlResult);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void toJson() throws IOException {
		final CResult<MessageResult> result = new CResult<MessageResult>();
		String msg = result.toJson();
		logger.info(msg);
		assertEquals("opCode not Equals",result.fromJson(msg).getCode(),"0");
	}
}
