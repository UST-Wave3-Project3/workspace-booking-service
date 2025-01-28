package com.project.workspace_booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkspaceDto {

	private int workspaceId;
    private String seatNumber;
    private boolean workspaceAvailable;
    
}
