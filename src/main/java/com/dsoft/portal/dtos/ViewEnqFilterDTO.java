package com.dsoft.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ViewEnqFilterDTO {
    private String studentName;
    private String courseMode;
    private String enqStatus;
    private String courseName;
}
