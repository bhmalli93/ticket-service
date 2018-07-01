package com.walmart.ticketservice.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import com.walmart.ticketservice.constants.Constants;
import com.walmart.ticketservice.impl.TicketServiceImpl;
import com.walmart.ticketservice.model.Customer;
import com.walmart.ticketservice.model.Seat;
import com.walmart.ticketservice.model.SeatHold;
import com.walmart.ticketservice.model.Venue;
import com.walmart.ticketservice.service.TicketService;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TicketServiceStepDefs {

	private Venue venue = null;
	private TicketService service = null;
	private SeatHold seatHold, seatHold1,seatHold2 = null;
	private String status = null;

	
	  @Before 
	  public void venue() { venue = new Venue("Venue-01","Venue1",10,10);
	  service = new TicketServiceImpl(venue,new ArrayList<Customer>()); /* create service with venue and empty customer list */
	  System.out.println("Created Seats Completed"+service.numSeatsAvailable()); 
	  }
	 

	/*@Given("^create a venue with row (\\d+) and col (\\d+)$")
	public void venue_is_created(int row, int col) {
		venue = new Venue("Venue-01", "Venue1", 10, 10);
		service = new TicketServiceImpl(venue,
				new ArrayList<Customer>());  create service with venue and empty customer list 
		System.out.println("Created Seats Completed" + service.numSeatsAvailable());
	}*/

	@Then("^I should have (\\d+) seats available$")
	public void I_should_have_100_seats(int expected) {
		assertEquals(expected, service.numSeatsAvailable());
	}

	@When("hold (\\d+) seats for customer (.+)$")
	public void hold_25_seats_for_customer(Integer noOfSeatsToHold, String customer) {
		seatHold = service.findAndHoldSeats(noOfSeatsToHold, customer);
		System.out.println("seatHoldId " + seatHold.getSeatHoldId());
	}

	@Then("^seathold id should not be null$")
	public void seat_hold_id_should_not_be_null() {
		assertNotNull(seatHold.getSeatHoldId());
	}

	@Then("^num of seats held (\\d+)$")
	public void num_of_seats_hold(int expected) {
		assertEquals(expected, seatHold.getNumOfSeats());
	}

	@Then("^I should have remaining (\\d+) seats available$")
	public void I_should_have_85_seats(int expected) {
		assertEquals(expected, service.numSeatsAvailable());
	}

	@When("^reserve seats for customer invalid hold id (\\d+) and (.+)$")
	public void reserve_seats_for_invalid_hold_id(int holdId, String customerEmail) {
		status = service.reserveSeats(holdId, customerEmail);
	}

	@Then("^you should get an exception msg$")
	public void you_should_get_exception() {
		assertEquals(Constants.EXCEPTION_MSG, status);
		System.out.println(status);
	}

	@When("^Book Seats For HoldId (.+)$")
	public void book_seats_for_existing_hold_id(String customerEmail) {
		String reserverd = service.reserveSeats(seatHold.getSeatHoldId(), customerEmail);
		System.out.println(reserverd);
	}

	@Then("^I should see status as reserved for seats$")
	public void I_should_see_status_reserved() {
		for (Seat seat : seatHold.getSeats()) {
			assertEquals(Seat.SeatStatus.RESERVED, seat.getStatus());
			System.out.println(Arrays.asList(seat.getSeatId(), seat.getStatus()));

		}
	}

	@When("^I Hold seats for group booking$")
	public void hold_remaining_seats_for_customer() {
		seatHold1 = service.findAndHoldSeats(15, "hold1_test@gmail.com");
		System.out.println("seatHold1 " + seatHold1.getSeatHoldId());
		seatHold2 = service.findAndHoldSeats(15, "hold1_test@gmail.com");
		System.out.println("seatHold2 " + seatHold2.getSeatHoldId());
		service.reserveSeats(seatHold1.getSeatHoldId(), "hold1_test@gmail.com");
		service.reserveSeats(seatHold2.getSeatHoldId(), "hold1_test@gmail.com");
	}

	@Then("^I should see status as reserved for seats for group$")
	public void I_should_see_status_reserved_for_group() {
		for (Seat seat : seatHold1.getSeats()) {
			assertEquals(Seat.SeatStatus.RESERVED, seat.getStatus());
			System.out.println(Arrays.asList(seat.getSeatId(), seat.getStatus()));
		}
		for (Seat seat : seatHold2.getSeats()) {
			assertEquals(Seat.SeatStatus.RESERVED, seat.getStatus());
			System.out.println(Arrays.asList(seat.getSeatId(), seat.getStatus()));
		}
	}

	@Then("^remaining seats should be (\\d+)$")
	public void remaining_seats_should_be_0(int expected) {
		assertEquals(expected, service.numSeatsAvailable());
	}

	@When("^customer try to hold zero seats then seat hold should be null$")
	public void customer_try_to_hold_zero_seats() {
		Customer newCustomer = new Customer("Malli@gmail.com");
		newCustomer.setCustomerEmail("Malli@gmail.com");
		newCustomer.setCustomerId(5);
		newCustomer.setCustomerName("Mallikharjuna");
		SeatHold seatsHold = new SeatHold(newCustomer, 0);
		seatsHold = service.findAndHoldSeats(0, "Malli@gmail.com");
		assertNull(seatsHold);
	}
	
}
