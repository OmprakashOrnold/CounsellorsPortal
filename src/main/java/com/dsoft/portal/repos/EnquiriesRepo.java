package com.dsoft.portal.repos;

import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Enquiries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnquiriesRepo extends JpaRepository<Enquiries, Long> {

    @Query(value = "select  * from enquiries e where e.counsellorId = :counsellorId", nativeQuery = true)
    List<Enquiries> getDashboardDetails(Long counsellorId);
}
