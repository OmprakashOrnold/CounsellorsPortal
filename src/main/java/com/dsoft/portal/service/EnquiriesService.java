package com.dsoft.portal.service;

import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Enquiries;

import java.util.List;

public interface EnquiriesService {


    void  addEnquire(EnquireDTO enquireDTO);

    List<EnquireDTO> getAllEnquiriesByCounsellorId(Long counsellorId);

    List<EnquireDTO> getAllEnquiriesByFilter(EnquireDTO enquireDTO,Long counsellorId);

}
