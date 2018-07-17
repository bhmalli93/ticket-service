package com.ticketservice.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ticketservice.constants.Constants;
import com.ticketservice.impl.TicketServiceImpl;
import com.ticketservice.model.Customer;
import com.ticketservice.model.SeatHold;
import com.ticketservice.model.Venue;
import com.ticketservice.service.TicketService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceJunitTest {

	private static Venue venue = null;
	private static TicketService service = null;
	@BeforeClass
	public static void venue() {
		venue = new Venue("Venue-02", "Venue1", 5, 10);
		service = new TicketServiceImpl(venue,
				new ArrayList<Customer>()); /* create service with venue and empty customer list */
	}
	
	@Test
	public void test1NumOfSeatsAvailable() {
		int capacity = service.numSeatsAvailable();
		assertNotNull(capacity);
		assertEquals(50, capacity);
	}
	
	@Test
	public void test2FindAndHoldSeats() {
		SeatHold seatHold = service.findAndHoldSeats(10, "test_hold@gmail.com");
		assertNotNull(seatHold.getSeatHoldId());
		assertEquals(40, service.numSeatsAvailable());
	}	
	
	@Test
	public void test3ReserveSeats() {
		String status = service.reserveSeats(1, "test_hold@gmail.com");
		assertNotNull(status);
		assertEquals(40, service.numSeatsAvailable());
	}
	
	
	
	@Test
	public void test4ReserveSeatsForInvalidHoldId() {
		String status =	service.reserveSeats(5, "junk@gmail.com");
		assertEquals(Constants.EXCEPTION_MSG, status);		
	}
	
	@Test
	public void test5GroupHoldAndCommit() {
		SeatHold seatHold1 = service.findAndHoldSeats(9, "abc@gmail.com");
		SeatHold seatHold2 = service.findAndHoldSeats(11, "abc@gmail.com");
		service.reserveSeats(seatHold1.getSeatHoldId(), "abc@gmail.com");
		service.reserveSeats(seatHold2.getSeatHoldId(), "abc@gmail.com");
		assertNotNull(seatHold1.getSeatHoldId());
		assertNotNull(seatHold2.getSeatHoldId());
		assertEquals(20, service.numSeatsAvailable());		
	}
	
	@Test
	public void test6HoldMorethanCapacity() {
		int capacity = service.numSeatsAvailable();
		SeatHold seatHold = service.findAndHoldSeats(capacity+1, "mytest@gmail.com");
		assertNull(seatHold);
	}
	
	
	
	// Un Comment this code if you want to test Resetting Holds after specific time, for now we are resetting after every 2 minutes
 	/*@Test
	public void test7HoldAndResetAfterSetOfTime() throws InterruptedException {
		int beforeHold = service.numSeatsAvailable();
		SeatHold seatHold = service.findAndHoldSeats(5,"abcd@gmail.com");
		Thread.sleep(145000);
		assertEquals(beforeHold, service.numSeatsAvailable());	
	}*/
	
	
}
