package org.dearbinge.client.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.dearbinge.utils.Constants;
import com.dearbinge.utils.JsonUtil;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		String apiUrl = "http://localhost:8082/syncParkingBasicData";
		HttpPost httpPost = new HttpPost(apiUrl);
		List<NameValuePair> listPair = new ArrayList<NameValuePair>();
		// 填入各个表单域的值
		listPair.add(new BasicNameValuePair("secret_key", "0eca8f5373ca4866aec2f8e9d9367104"));
		listPair.add(new BasicNameValuePair("method", "dearbinge.parkingbasicdata.sync"));
		httpPost.setEntity(new UrlEncodedFormEntity(listPair));
		UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(listPair, "UTF-8");
		httpPost.setEntity(formEntity);
		CloseableHttpResponse response = httpclient.execute(httpPost);
		try {
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			// do something useful with the response body
			// and ensure it is fully consumed
			String result = EntityUtils.toString(entity, "UTF-8");
			System.out.println(result);
			JsonNode jsonNode = JsonUtil.getJsonNodeByKey(result, Constants.ERROR_RESPONSE);
			if (jsonNode != null) {
				System.out.println(jsonNode.toString());
			}
			EntityUtils.consume(entity);
		} finally {
			response.close();
		}
	}
}
