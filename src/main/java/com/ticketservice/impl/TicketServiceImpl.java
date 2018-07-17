package com.ticketservice.impl;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ticketservice.constants.Constants;
import com.ticketservice.model.Customer;
import com.ticketservice.model.Seat;
import com.ticketservice.model.SeatHold;
import com.ticketservice.model.Venue;
import com.ticketservice.service.TicketService;

public class TicketServiceImpl implements TicketService{

    private Venue venue;
    private List<Customer> customers;
    private HashSet<SeatHold> seatHoldSet;
    private ScheduledExecutorService scheduleExecutor;

    public TicketServiceImpl(Venue venue,List<Customer> customers) {
        this.venue = venue;
        this.customers = customers;
        this.seatHoldSet = new HashSet<SeatHold>();
        this.scheduleExecutor = Executors.newScheduledThreadPool(1);
        scheduleExecutor.scheduleAtFixedRate(()->{checkAndExpireSeatHolds();}, 0, Constants.MAX_HOLDING_TIME_IN_SECONDS, TimeUnit.SECONDS);
    }

    private void checkAndExpireSeatHolds() {
    	for(SeatHold sh:this.seatHoldSet){
            if(sh.getExpiresAt().isBefore(ZonedDateTime.now(Constants.DEFAULT_TIME_ZONE))){
                for(Seat s:sh.getSeats()) s.setStatus(Seat.SeatStatus.AVAILABLE);
                this.seatHoldSet.remove(sh);
            }
        }
    }

    private int getSeatsByStatus(Seat.SeatStatus status){
        int count = 0;
        Map<Integer, Seat> seatMap = venue.getSeatMap();
        for(Integer i:seatMap.keySet()){
            if(seatMap.get(i).getStatus()==status) count+=1;
        }
        return count;
    }

    public int numSeatsAvailable() {
        return getSeatsByStatus(Seat.SeatStatus.AVAILABLE);
    }

    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
        if(numSeats<=0) return null;
        Map<Integer, Seat> seatMap = venue.getSeatMap();
        Seat seat;
        List<Seat> holdedSeats = new ArrayList<Seat>();
        int firstSeatAvailable = getFirstAvailableSeat();
        if(firstSeatAvailable==-1 || numSeats>numSeatsAvailable()) return null;
        for(int i=0;i<numSeats;i++,firstSeatAvailable++){
            seat = seatMap.get(firstSeatAvailable);
            seat.setStatus(Seat.SeatStatus.HOLDED);
            seatMap.put(firstSeatAvailable,seat);
            holdedSeats.add(seat);
        }
        Customer customer = getOrCreateCustomer(customerEmail);
        SeatHold seatHold = new SeatHold(customer,numSeats,holdedSeats);
        seatHoldSet.add(seatHold);
        return seatHold;
    }

    private Customer getOrCreateCustomer(String customerEmail) {
        for(Customer cust:customers){
            if(cust.getCustomerEmail().equals(customerEmail)) return cust;
        }
        return new Customer(customerEmail);
    }

    private int getFirstAvailableSeat() {
        Map<Integer, Seat> seatMap = venue.getSeatMap();
        for(Integer i:seatMap.keySet()){
            if(seatMap.get(i).getStatus()== Seat.SeatStatus.AVAILABLE) return i;
        }
        return -1; /*when venue is full*/
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
        SeatHold seatHold = getSeatHold(seatHoldId,customerEmail);
        if(seatHold==null) return "Invalid SeatHoldId or Invalid Customer Email. Both should be valid inorder to reserve seats.";
        for(Seat seat:seatHold.getSeats()){
            this.venue.getSeatMap().get(seat.getSeatId()).setStatus(Seat.SeatStatus.RESERVED);
        }
        return "Seats "+seatHold.getSeats()+" are successfully reserved. Confirmation will be sent to"+customerEmail;
    }

    private SeatHold getSeatHold(int seatHoldId,String customerEmail) {
        for(SeatHold sh:this.seatHoldSet){
            if(sh.getSeatHoldId()==seatHoldId&&sh.getCustomer().getCustomerEmail().equals(customerEmail)) return sh;
        }
        return null;
    }
}
