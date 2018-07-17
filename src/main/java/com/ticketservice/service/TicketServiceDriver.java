package com.ticketservice.service;

import java.util.ArrayList;

import com.ticketservice.impl.TicketServiceImpl;
import com.ticketservice.model.Customer;
import com.ticketservice.model.SeatHold;
import com.ticketservice.model.Venue;

public class TicketServiceDriver {

    public static void main(String args[]) throws InterruptedException{

        Venue venue = new Venue("Venue-01","Venue1",10,10);
        TicketServiceImpl service = new TicketServiceImpl(venue,new ArrayList<Customer>()); /* create service with venue and empty customer list*/
        System.out.println("Seat Available::"+service.numSeatsAvailable());/*should be 100*/
        SeatHold sh = service.findAndHoldSeats(5,"email@gmail.com");                  
    }
}
