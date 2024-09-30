package com.dsoft.portal.service.Impl;

import com.dsoft.portal.dtos.CounsellorDTO;
import com.dsoft.portal.entities.Counsellor;
import com.dsoft.portal.repos.CounsellorRepo;
import com.dsoft.portal.service.CounsellorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounsellorServiceImpl implements CounsellorsService {

    private final CounsellorRepo counsellorsRepo;
    private final ModelMapper modelMapper;

    @Override
    public boolean register(CounsellorDTO counsellorDTO) {
        try {
            // Check if email already exists
            if (counsellorsRepo.existsByEmail(counsellorDTO.getEmail())) {
                log.warn("Attempted registration with existing email: {}", counsellorDTO.getEmail());
                return false;
            }

            // Map DTO to entity
            Counsellor counsellor = modelMapper.map(counsellorDTO, Counsellor.class);

            // Save new counselor
            counsellorsRepo.save( counsellor );
            log.info("Registering new counselor: {}", counsellor.getEmail());
            return true;
        } catch (Exception e) {
            log.error("Error during registration", e);
            throw new RuntimeException("Failed to register counselor", e);
        }
    }

}
