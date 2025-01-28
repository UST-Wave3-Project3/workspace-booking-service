package com.project.workspace_booking_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.workspace_booking_service.dto.WorkspaceBookingResponseDto;
import com.project.workspace_booking_service.repository.entity.WorkspaceBooking;
import com.project.workspace_booking_service.service.WorkspaceBookingService;

@RestController
@RequestMapping("/api/workspace-bookings")
public class WorkspaceBookingController {
	@Autowired
    private WorkspaceBookingService workspaceBookingService;

    @PostMapping
    public ResponseEntity<WorkspaceBooking> createWorkspaceBooking(@RequestBody WorkspaceBooking workspaceBooking) {
        WorkspaceBooking createdBooking = workspaceBookingService.createWorkspaceBooking(workspaceBooking);
        return ResponseEntity.ok(createdBooking);
    }
    
    @DeleteMapping("/{workspaceBookingId}")
    public ResponseEntity<Void> deleteWorkspaceBooking(@PathVariable int workspaceBookingId) {
        workspaceBookingService.deleteWorkspaceBooking(workspaceBookingId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{workspaceBookingId}")
    public ResponseEntity<WorkspaceBooking> modifyWorkspaceBooking(
            @PathVariable int workspaceBookingId,
            @RequestBody WorkspaceBooking updatedBooking) {
       WorkspaceBooking modifiedBooking = workspaceBookingService.modifyWorkspaceBooking(workspaceBookingId, updatedBooking);
        return ResponseEntity.ok(modifiedBooking);
    }

    @GetMapping("/booking/{workspaceBookingId}")
    public ResponseEntity<WorkspaceBooking> viewWorkspaceBooking(@PathVariable int workspaceBookingId) {
        WorkspaceBooking booking = workspaceBookingService.viewWorkspaceBooking(workspaceBookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<List<WorkspaceBooking>> viewAllWorkspaceBookings() {
        List<WorkspaceBooking> bookings = workspaceBookingService.viewAllWorkspaceBookings();
        return ResponseEntity.ok(bookings);
    }
    
    @GetMapping("/{workspaceBookingId}")
    public WorkspaceBookingResponseDto getWorkspaceBookingWithUserDetails(@PathVariable int workspaceBookingId) {
        return workspaceBookingService.getWorkspaceBookingWithUserDetails(workspaceBookingId);
    }
    
    @GetMapping("/booking-date")
	public ResponseEntity<List<WorkspaceBooking>> getWorkspaceByBookingDate(@RequestParam("wBookingDate") LocalDate bookingDate){
		return new ResponseEntity<List<WorkspaceBooking>>(workspaceBookingService.getWorkspaceByBookingDate(bookingDate),HttpStatus.OK);
	}

}
