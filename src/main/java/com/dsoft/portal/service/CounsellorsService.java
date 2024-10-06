package com.dsoft.portal.service;

import com.dsoft.portal.dtos.CounsellorDTO;
import com.dsoft.portal.dtos.DashBoardResponseDTO;
import com.dsoft.portal.dtos.EnquireDTO;

public interface CounsellorsService {

     Boolean registerCounsellor(CounsellorDTO counsellorsDTO) throws Exception;

     Boolean loginCounsellor(CounsellorDTO counsellorsDTO) throws Exception;

     DashBoardResponseDTO getDashboardDetails(Long counsellorId);



}
