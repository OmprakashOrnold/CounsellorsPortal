package com.dsoft.portal.service;

import com.dsoft.portal.dtos.CounsellorRequest;
import com.dsoft.portal.dtos.DashBoardResponseDTO;
import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Counsellor;

public interface CounsellorsService {

     Boolean registerCounsellor(CounsellorRequest counsellorRequest) throws Exception;

     Counsellor loginCounsellor(CounsellorRequest counsellorRequest) throws Exception;

     DashBoardResponseDTO getDashboardDetails(Long counsellorId);



}
