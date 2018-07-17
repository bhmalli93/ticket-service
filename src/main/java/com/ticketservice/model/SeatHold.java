package com.ticketservice.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.ticketservice.constants.Constants;

public class SeatHold {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int seatHoldId;
    private Customer customer;
    private int numOfSeats;
    private List<Seat> seats;
    private ZonedDateTime expiresAt;

    public SeatHold(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public SeatHold( Customer customer, int numOfSeats) {
        this.seatHoldId = count.incrementAndGet();
        this.customer = customer;
        this.numOfSeats = numOfSeats;
        this.expiresAt = ZonedDateTime.now(Constants.DEFAULT_TIME_ZONE).plusSeconds(Constants.MAX_HOLDING_TIME_IN_SECONDS);
    }

    public SeatHold(Customer customer, int numOfSeats, List<Seat> seats) {
        this.seatHoldId = count.incrementAndGet();
        this.customer = customer;
        this.numOfSeats = numOfSeats;
        this.seats = seats;
        this.expiresAt = ZonedDateTime.now(Constants.DEFAULT_TIME_ZONE).plusSeconds(Constants.MAX_HOLDING_TIME_IN_SECONDS);
    }

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public ZonedDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ZonedDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "SeatHold{" +
                "seatHoldId=" + seatHoldId +
                ", customer=" + customer +
                ", numOfSeats=" + numOfSeats +
                ", seats=" + seats +
                ", expiresAt=" + expiresAt +
                '}';
    }
}
