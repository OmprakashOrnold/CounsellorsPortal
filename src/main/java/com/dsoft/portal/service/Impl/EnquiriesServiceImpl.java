package com.dsoft.portal.service.Impl;

import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Enquiries;
import com.dsoft.portal.repos.EnquiriesRepo;
import com.dsoft.portal.service.EnquiriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnquiriesServiceImpl implements EnquiriesService {

    private final EnquiriesRepo enquiriesRepo;
    private final ModelMapper modelMapper;

    @Override
    public void addEnquire(EnquireDTO enquireDTO) {

    }

    @Override
    public List<Enquiries> getAllEnquiriesByCounsellorId(Long counsellorId) {
        return List.of();
    }

    @Override
    public List<Enquiries> getAllEnquiriesByFilter(EnquireDTO enquireDTO, Long counsellorId) {
        return List.of();
    }
}
