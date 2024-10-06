package com.dsoft.portal.service.Impl;

import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Enquiries;
import com.dsoft.portal.repos.EnquiriesRepo;
import com.dsoft.portal.service.EnquiriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnquiriesServiceImpl implements EnquiriesService {

    private final EnquiriesRepo enquiriesRepo;
    private final ModelMapper modelMapper;

    @Override
    public void addEnquire(EnquireDTO enquireDTO) {
        log.info("Attempting to add new enquiry: {}", enquireDTO);
        try {
            Enquiries enquiries = modelMapper.map(enquireDTO, Enquiries.class);
            saveEnquiry(enquiries);
            log.info("Enquiry successfully added");
        } catch (Exception e) {
            log.error("Error adding enquiry: {}", enquireDTO, e);
            throw new RuntimeException("Failed to add enquiry", e);
        }

    }

    @Override
    public List<EnquireDTO> getAllEnquiriesByCounsellorId(Long counsellorId) {
        List<EnquireDTO> enquireDTOS = null;
        log.info("Fetching all enquiries for counselor ID: {}", counsellorId);
        try {
            List<Enquiries> enquiries = enquiriesRepo.findAll();
            if (!CollectionUtils.isEmpty(enquiries)) {
                log.debug("Found {} enquiries for counselor ID: {}", enquiries.size(), counsellorId);
                enquireDTOS = enquiries.stream()
                        .map(enquiry -> modelMapper.map(enquiry, EnquireDTO.class))
                        .collect(Collectors.toList());
                log.debug("Successfully mapped {} enquiries to DTOs", enquireDTOS.size());
            } else {
                log.warn("No enquiries found for counselor ID: {}", counsellorId);
            }
            return enquireDTOS;
        } catch (Exception e) {
            log.error("Error fetching enquiries for counselor ID: {}", counsellorId, e);
            throw new RuntimeException("Failed to fetch enquiries", e);
        }
    }

    @Override
    public List<EnquireDTO> getAllEnquiriesByFilter(EnquireDTO enquireDTO, Long counsellorId) {
        return List.of();
    }

    private void saveEnquiry(Enquiries enquiries) {
        log.debug("Saving enquiry: {}", enquiries);
        enquiriesRepo.save(enquiries);
        log.debug("Enquiry saved successfully: {}", enquiries.getEnqId());
    }
}
