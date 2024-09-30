package com.dsoft.portal.repos;

import com.dsoft.portal.dtos.EnquireDTO;
import com.dsoft.portal.entities.Enquiries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiriesRepo extends JpaRepository<Enquiries, Long> {

}
