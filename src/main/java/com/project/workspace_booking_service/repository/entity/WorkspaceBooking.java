package com.project.workspace_booking_service.repository.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="workspace_booking")
public class WorkspaceBooking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="w_booking_id")
	private int wbookingId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="workspace_id")
	private int workspaceId;
	
	@Column(name="w_booking_date")
	private LocalDate workspaceBookingDate;
	
	@Column(name="w_start_time")
	private LocalTime wstartTime;
	
	@Column(name="w_end_time")
	private LocalTime wendTime;

}
