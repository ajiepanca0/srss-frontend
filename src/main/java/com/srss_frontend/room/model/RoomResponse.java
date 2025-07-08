package com.srss_frontend.room.model;

import java.util.List;

import com.srss_frontend.base.model.Status;


public class RoomResponse {

	public Status status;
	
	public List<Room> room;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

}
