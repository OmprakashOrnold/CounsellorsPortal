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
    private Long totalEnquires;
    private Long openEnquires;
    private Long enrolledEnquires;
    private Long lostEnquires;
}
