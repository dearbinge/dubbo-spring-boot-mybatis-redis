package com.dearbinge.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.dearbinge.data.domain.ParkingBasicDataSyncResponse;
import com.dearbinge.service.InfrastructDeal;
import com.dearbinge.utils.JsonUtil;
import com.dearbinge.utils.ReturnCode;

@Component
public class ParkingBasicDataSync implements InfrastructDeal {
	@Override
	public String accept(HttpServletRequest request) throws JsonProcessingException {
		// TODO Auto-generated method stub
		ParkingBasicDataSyncResponse parkingBasicDataSyncResponse = new ParkingBasicDataSyncResponse();
		parkingBasicDataSyncResponse.setCode(ReturnCode.SUCCESS.ordinal());
		parkingBasicDataSyncResponse.setMessage(ReturnCode.SUCCESS.toString());
		parkingBasicDataSyncResponse.setPark_id("012e5ef643804cfa9e364fe6a5f90668");
		parkingBasicDataSyncResponse.setSuccessful(true);
		// parkingBasicDataSyncResponse.setMsg("this is first spring boot
		// getbean method!这是第一个SPRING BOOT获取BEAN的方法！");
		return JsonUtil.convertObject2String(parkingBasicDataSyncResponse);
	}
}
