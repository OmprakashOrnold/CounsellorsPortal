package com.dsoft.portal.service.Impl;

import com.dsoft.portal.dtos.CounsellorDTO;
import com.dsoft.portal.dtos.DashBoardResponseDTO;
import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Counsellor;
import com.dsoft.portal.entities.Enquiries;
import com.dsoft.portal.repos.CounsellorRepo;
import com.dsoft.portal.repos.EnquiriesRepo;
import com.dsoft.portal.service.CounsellorsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CounsellorServiceImpl implements CounsellorsService {

    private final CounsellorRepo counsellorsRepo;
    private final EnquiriesRepo enquiriesRepo;
    private final ModelMapper modelMapper;

    @Override
    public Boolean registerCounsellor(CounsellorDTO counsellorDto) throws Exception {
        boolean isRegistered = false;
        try {
            validateRegistrationInput(counsellorDto);

            if (counsellorsRepo.existsByEmail(counsellorDto.getEmail())) {
                log.warn("Attempted registration with existing email: {}", counsellorDto.getEmail());
                throw new Exception("Email already exists");
            }

            Counsellor counsellor = modelMapper.map(counsellorDto, Counsellor.class);
            Counsellor savedCounsellor = counsellorsRepo.save(counsellor);

            if (!ObjectUtils.isEmpty(savedCounsellor)) {
                log.info("Successfully registered new counsellor: {}", savedCounsellor.getEmail());
                isRegistered = true;
            }

            return isRegistered;
        } catch (Exception e) {
            log.error("Error during counsellor registration", e);
            throw new Exception("Failed to register counsellor");
        }
    }


    @Override
    public Boolean loginCounsellor(CounsellorDTO counsellorsDTO) throws Exception {
        try {
            String email = counsellorsDTO.getEmail();
            String password = counsellorsDTO.getPassword();
            Counsellor counsellor = counsellorsRepo.findByEmail(email);
            if (!ObjectUtils.isEmpty(counsellor)) {
                if (password.toLowerCase().equals(counsellor.getPassword().toLowerCase())) {
                    log.info("Successful login attempt for counsellor: {}", email);
                    return true;
                } else {
                    log.warn("Failed login attempt due to incorrect password for counsellor: {}", email);
                    return false;
                }
            } else {
                log.warn("No counsellor found with email: {}", email);
                return false;
            }
        } catch (Exception e) {
            log.error("Error during login", e);
            throw new Exception("Login failed", e);
        }
    }

    public DashBoardResponseDTO getDashboardDetails(Long counselorId) {
        try {
            log.info("Fetching dashboard details for counselor ID: {}", counselorId);

            List<Enquiries> enquiries = enquiriesRepo.getDashboardDetails(counselorId);

            if (!CollectionUtils.isEmpty(enquiries)) {
                int totalEnquiries = enquiries.size();
                int enrolledEnquiriesCount = countEnquiriesByStatus(enquiries, "ENROLLED");
                int openEnquiriesCount = countEnquiriesByStatus(enquiries, "OPEN");
                int lostEnquiriesCount = countEnquiriesByStatus(enquiries, "LOST");

                DashBoardResponseDTO dashboardResponse = DashBoardResponseDTO.builder()
                        .totalEnquires(totalEnquiries)
                        .openEnquires(openEnquiriesCount)
                        .lostEnquires(lostEnquiriesCount)
                        .enrolledEnquires(enrolledEnquiriesCount)
                        .build();

                log.info("Dashboard details fetched successfully for counselor ID: {}", counselorId);
                return dashboardResponse;
            } else {
                log.warn("No enquiries found for counselor ID: {}", counselorId);
                return null;
            }
        } catch (Exception e) {
            log.error("Error fetching dashboard details for counselor ID: {}", counselorId, e);
            throw new RuntimeException("Failed to fetch dashboard details", e);
        }
    }

    private int countEnquiriesByStatus(List<Enquiries> enquiries, String status) {
        return (int) enquiries.stream().filter(enq -> enq.getEnqStatus().equals(status)).count();
    }


    private void validateRegistrationInput(CounsellorDTO dto) {
        if (dto == null || ObjectUtils.isEmpty(dto.getEmail()) || ObjectUtils.isEmpty(dto.getPassword())) {
            throw new IllegalArgumentException("Invalid input for registration");
        }
    }
}

