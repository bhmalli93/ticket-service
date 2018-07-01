package com.walmart.ticketservice.service;

import java.util.ArrayList;

import com.walmart.ticketservice.impl.TicketServiceImpl;
import com.walmart.ticketservice.model.Customer;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.Venue;

public class TicketServiceDriver {

    public static void main(String args[]){

        Venue venue = new Venue("Venue-01","Venue1",10,10);
        TicketServiceImpl service = new TicketServiceImpl(venue,new ArrayList<Customer>()); /* create service with venue and empty customer list*/
        System.out.println("Seat Available::"+service.numSeatsAvailable());/*should be 100*/
        SeatHold sh = service.findAndHoldSeats(5,"email@gmail.com");
        System.out.println("Seats after hold::"+service.numSeatsAvailable());/*should be 95*/
        System.out.println(sh.toString());
        System.out.println(service.reserveSeats(200,"junk@gmail.com"));
        System.out.println(service.reserveSeats(sh.getSeatHoldId(),sh.getCustomer().getCustomerEmail()));
    }
}
