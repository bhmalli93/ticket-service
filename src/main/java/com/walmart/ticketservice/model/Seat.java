package com.walmart.ticketservice.model;

public class Seat {

	private int seatId;
	private int row;
	private int column;
	private SeatStatus status;

	public Seat(int seatId, int row, int column) {
		this.seatId = seatId;
		this.row = row;
		this.column = column;
		this.status = SeatStatus.AVAILABLE;
	}

	public Seat(int seatId, int row, int column, SeatStatus status) {
		this.seatId = seatId;
		this.row = row;
		this.column = column;
		this.status = status;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public SeatStatus getStatus() {
		return status;
	}

	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Seat{" + "seatId=" + seatId + ", row=" + row + ", column=" + column + ", status=" + status + '}';
	}

	public enum SeatStatus {
		AVAILABLE, HOLDED, RESERVED
	}

}
