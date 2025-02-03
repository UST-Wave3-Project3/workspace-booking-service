package com.project.workspace_booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.workspace_booking_service.dto.WorkspaceDto;

@FeignClient(name = "workspace-service", url = "http://localhost:9090/api/workspaces")
public interface WorkspaceClient {

	@GetMapping("{workspaceId}")
    WorkspaceDto getWorkspaceById(@PathVariable int workspaceId);
	
	@PutMapping
    WorkspaceDto updateWorkspace(@RequestBody WorkspaceDto workspace);
}
