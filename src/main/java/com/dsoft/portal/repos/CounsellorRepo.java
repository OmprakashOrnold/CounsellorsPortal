package com.dsoft.portal.repos;

import com.dsoft.portal.entities.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CounsellorRepo extends JpaRepository<Counsellor,Long> {

   boolean existsByEmail(String email);

   List<Counsellor> findByEmail(String email);
}
