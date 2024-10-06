package com.dsoft.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnquireDTO {
    private String studentName;
    private String studentPhoto;
    private String courseName;
    private String courseMode;
    private String enqStatus;
}
