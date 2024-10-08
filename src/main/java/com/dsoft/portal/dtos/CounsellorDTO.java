package com.dsoft.portal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CounsellorDTO {
    private String name;
    private String email;
    private String password;
    private String phNo;
}
