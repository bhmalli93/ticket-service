package com.walmart.ticketservice.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.walmart.ticketservice.constants.Constants;
import com.walmart.ticketservice.impl.TicketServiceImpl;
import com.walmart.ticketservice.model.Customer;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.Venue;
import com.walmart.ticketservice.service.TicketService;
@FixMethodOrder(MethodSorters.JVM)
public class TicketServiceJunitTest {

	private Venue venue = null;
	private TicketService service = null;
	@Before
	public void venue() {
		venue = new Venue("Venue-02", "Venue1", 5, 10);
		service = new TicketServiceImpl(venue,
				new ArrayList<Customer>()); /* create service with venue and empty customer list */
	}
	
	@Test
	public void testNumOfSeatsAvailable() {
		int capacity = service.numSeatsAvailable();
		assertNotNull(capacity);
		assertEquals(50, capacity);
	}
	
	@Test
	public void testFindAndHoldSeatsAndReserveSeats() {
		SeatHold seatHold = service.findAndHoldSeats(10, "test_hold@gmail.com");
		assertNotNull(seatHold.getSeatHoldId());
		assertEquals(40, service.numSeatsAvailable());
		String status = service.reserveSeats(seatHold.getSeatHoldId(), "test_hold@gmail.com");
		System.out.println(""+status);
		assertEquals(40, service.numSeatsAvailable());
	}
	
	
	@Test
	public void testReserveSeatsForInvalidHoldId() {
		String status =	service.reserveSeats(5, "junk@gmail.com");
		assertEquals(Constants.EXCEPTION_MSG, status);		
	}
	
	@Test
	public void testGroupHoldAndCommit() {
		SeatHold seatHold = service.findAndHoldSeats(10, "abc@gmail.com");
		SeatHold seatHold2 = service.findAndHoldSeats(10, "abc@gmail.com");
		service.reserveSeats(seatHold.getSeatHoldId(), "abc@gmail.com");
		service.reserveSeats(seatHold2.getSeatHoldId(), "abc@gmail.com");
		assertNotNull(seatHold.getSeatHoldId());
		assertNotNull(seatHold2.getSeatHoldId());
		assertEquals(30, service.numSeatsAvailable());		
	}
	
	
	// Un Comment this code if you want to test Resetting Holds after specific time, for now we are resetting after every 2 minutes
	/*@Test
	public void testHoldAndResetAfterSetOfTime() throws InterruptedException {
		SeatHold seatHold = service.findAndHoldSeats(10, "abcd@gmail.com");
		assertNotNull(seatHold.getSeatHoldId());
		Thread.sleep(125000);
		System.out.println("Seats After Restting Holds after every 2 minutes "+service.numSeatsAvailable());
		assertEquals(50, service.numSeatsAvailable());	
	}*/
	
	
}
