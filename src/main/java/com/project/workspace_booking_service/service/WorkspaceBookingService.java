package com.project.workspace_booking_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.workspace_booking_service.client.UserClient;
import com.project.workspace_booking_service.client.WorkspaceClient;
import com.project.workspace_booking_service.dto.UserDto;
import com.project.workspace_booking_service.dto.WorkspaceBookingResponseDto;
import com.project.workspace_booking_service.dto.WorkspaceDto;
import com.project.workspace_booking_service.repository.WorkspaceBookingRepository;
import com.project.workspace_booking_service.repository.entity.WorkspaceBooking;

@Service
public class WorkspaceBookingService {

	@Autowired
    private WorkspaceBookingRepository workspaceBookingRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private WorkspaceClient workspaceClient;
    
    public WorkspaceBooking createWorkspaceBooking(WorkspaceBooking workspaceBooking) {
        UserDto user = userClient.getUserById(workspaceBooking.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found with ID: " + workspaceBooking.getUserId());
        }
        WorkspaceDto workspace = workspaceClient.getWorkspaceById(workspaceBooking.getWorkspaceId());
        if (workspace == null || !workspace.isWorkspaceAvailable()) {
            throw new RuntimeException("Workspace not available with ID: " + workspaceBooking.getWorkspaceId());
        }
        return workspaceBookingRepository.save(workspaceBooking);
    }
        
    public void deleteWorkspaceBooking(int workspaceBookingId) {
        if (!workspaceBookingRepository.existsById(workspaceBookingId)) {
            throw new RuntimeException("Booking not found with ID: " + workspaceBookingId);
        }
        workspaceBookingRepository.deleteById(workspaceBookingId);
    }
    
    public WorkspaceBooking modifyWorkspaceBooking(int workspaceBookingId, WorkspaceBooking updatedBooking) {
        Optional<WorkspaceBooking> existingBookingOpt = workspaceBookingRepository.findById(workspaceBookingId);
        if (existingBookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found with ID: " + workspaceBookingId);
        }

        WorkspaceBooking existingBooking = existingBookingOpt.get();
        existingBooking.setWorkspaceId(updatedBooking.getWorkspaceId());
        existingBooking.setUserId(updatedBooking.getUserId());
        existingBooking.setWorkspaceBookingDate(updatedBooking.getWorkspaceBookingDate());
        existingBooking.setWstartTime(updatedBooking.getWstartTime());
        existingBooking.setWendTime(updatedBooking.getWendTime());

        return workspaceBookingRepository.save(existingBooking);
    }

    public WorkspaceBooking viewWorkspaceBooking(int bookingId) {
        return workspaceBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));
    }
    
    public List<WorkspaceBooking> viewAllWorkspaceBookings() {
        return workspaceBookingRepository.findAll();
    }
    
    public WorkspaceBookingResponseDto getWorkspaceBookingWithUserDetails(int bookingId) {
        Optional<WorkspaceBooking> workspaceBookingOpt = workspaceBookingRepository.findById(bookingId);
        
        if (workspaceBookingOpt.isPresent()) {
            WorkspaceBooking workspaceBooking = workspaceBookingOpt.get();
 
            UserDto user = userClient.getUserById(workspaceBooking.getUserId());

            WorkspaceDto workspace = workspaceClient.getWorkspaceById(workspaceBooking.getWorkspaceId());

            WorkspaceBookingResponseDto response = new WorkspaceBookingResponseDto();
            response.setWbookingId(workspaceBooking.getWbookingId());
            response.setWorkspaceBookingDate(workspaceBooking.getWorkspaceBookingDate());
            response.setWstartTime(workspaceBooking.getWstartTime());
            response.setWendTime(workspaceBooking.getWendTime());
            
            response.setUserId(workspaceBooking.getUserId());
            response.setWorkspaceId(workspaceBooking.getWorkspaceId());
            
            response.setUser(user);
            response.setWorkspace(workspace);

            return response;
        } else {
            throw new RuntimeException("Workspace Booking not found with ID: " + bookingId);
        }
    }
    public List<WorkspaceBooking> getWorkspaceByBookingDate(LocalDate bookingDate) {
        return workspaceBookingRepository.findByWorkspaceBookingDate(bookingDate);
    }
    
}
