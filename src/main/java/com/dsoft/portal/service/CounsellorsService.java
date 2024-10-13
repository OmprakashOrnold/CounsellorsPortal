package com.dsoft.portal.service;

import com.dsoft.portal.dtos.CounsellorDTO;
import com.dsoft.portal.dtos.DashBoardResponseDTO;
import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Counsellor;

public interface CounsellorsService {

     Boolean registerCounsellor(CounsellorDTO counsellorsDTO) throws Exception;

     Counsellor loginCounsellor(CounsellorDTO counsellorsDTO) throws Exception;

     DashBoardResponseDTO getDashboardDetails(Long counsellorId);



}
