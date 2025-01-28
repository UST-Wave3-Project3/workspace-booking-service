package com.project.workspace_booking_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkspaceBookingResponseDto {
	
	private int wbookingId;
	private int userId;
	private int workspaceId;
	private LocalDate workspaceBookingDate;
	private LocalTime wstartTime;
	private LocalTime wendTime;
	private UserDto user;
	private WorkspaceDto workspace;

}
