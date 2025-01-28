package com.project.workspace_booking_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.workspace_booking_service.repository.entity.WorkspaceBooking;

@Repository
public interface WorkspaceBookingRepository extends JpaRepository<WorkspaceBooking, Integer>{
	
	List<WorkspaceBooking> findByWorkspaceBookingDate(LocalDate bookingDate);

}
