package com.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.zzn.util.HttpUtil;

public class TestAvatar {

	public static void main(String[] args) throws JsonProcessingException,
			IOException {
		String urlString = "http://open-test.bong.cn/1/userInfo/avatar/22088882811149902693?access_token=cbd652c9-d70c-483c-85cc-48e2bbef0502";
		String str = "{" + HttpUtil.postHttp(urlString);

		ObjectMapper mapper = new ObjectMapper();
		// 解析器支持解析单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
		// 解析器支持解析结束符
		mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

		JsonNode rootNode = mapper.readTree(str);
		String sb = rootNode.get("value").getTextValue();
		File file = new File("/Users/zzn/workdoc/4.jpg");
		FileUtils.writeByteArrayToFile(file, Base64.decodeBase64(sb));

		// byte[] avatar = rootNode.get("value").getBinaryValue();
		// File file = new File("/Users/zzn/workdoc/2.jpg");
		// FileUtils.writeByteArrayToFile(file, avatar);
	}
}
