package com.walmart.ticketservice.model;

import java.util.HashMap;
import java.util.Map;

public class Venue {

	private String venueId;
	private String venueName;
	private int rows;
	private int columns;
	private Map<Integer, Seat> seatMap;

	public Venue(String venueId, int rows, int columns) {
		this.venueId = venueId;
		this.rows = rows;
		this.columns = columns;
		this.seatMap = buildSeatMap(rows, columns);
	}

	public Venue(String venueId, String venueName, int rows, int columns) {
		this.venueId = venueId;
		this.venueName = venueName;
		this.rows = rows;
		this.columns = columns;
		this.seatMap = buildSeatMap(rows, columns);
	}

	private Map<Integer, Seat> buildSeatMap(int rows, int columns) {
		Map<Integer, Seat> seatMap = new HashMap<Integer, Seat>();
		int seatNum = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 1; j <= columns; j++) {
				seatNum = i * columns + j;
				seatMap.put(seatNum, new Seat(seatNum, i,
						j));/* creating seat with seatNum as seatId, row as row and column as columns */
			}
		}
		return seatMap;
	}

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Map<Integer, Seat> getSeatMap() {
		return seatMap;
	}

	public void setSeatMap(Map<Integer, Seat> seatMap) {
		this.seatMap = seatMap;
	}

	@Override
	public String toString() {
		return "Venue{" + "venueId='" + venueId + '\'' + ", venueName='" + venueName + '\'' + ", rows=" + rows
				+ ", columns=" + columns + ", seatMap=" + seatMap + '}';
	}

}
