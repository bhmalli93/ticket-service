@myFeature
Feature: I need to Hold and Book Ticket 	
	@Scenario1 
 	Scenario: Ticket Service
 	# getAvailableSeats 	
 	# Given create a venue with row 10 and col 10
    Then I should have 100 seats available
    
    # findAndHold best available seats   
	When hold 25 seats for customer hold_tickets@gmail.com
	Then seathold id should not be null
	Then num of seats held 25	
	Then I should have remaining 75 seats available
	
	#ReserveSeatsForInavalidHoldId  
	When reserve seats for customer invalid hold id 200 and junk@email.com
	Then you should get an exception msg
	
	#ReserveSeatsForExistingHoldId 
	When Book Seats For HoldId hold_tickets@gmail.com
	Then I should see status as reserved for seats
	
	#Reserve And Commit a specific group
	When I Hold seats each for group booking
	Then I should see status as reserved for seats for group	
	Then remaining seats should be 45
