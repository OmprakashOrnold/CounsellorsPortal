package com.dsoft.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardResponseDTO {
    private Integer totalEnquires;
    private Integer openEnquires;
    private Integer enrolledEnquires;
    private Integer lostEnquires;
}
