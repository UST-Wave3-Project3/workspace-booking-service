package com.project.workspace_booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {

	private Long userId;
    private String userFirstName;
    private String userEmail;
}
