package com.srss_frontend.room.model;

import java.time.LocalDate;

public class Room {

	
	private Long idRoom;

	private String roomCode;

	private String roomName;

	private Integer bedCapacity;

	private String description;

	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Integer getBedCapacity() {
		return bedCapacity;
	}

	public void setBedCapacity(Integer bedCapacity) {
		this.bedCapacity = bedCapacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
