#Ticketing Service

Core Java Application - Designed Solution based on Not to use any database to store data or use Data Structures to Store data.
    
   Ticket Service is service which helps customers to find available seats, hold them and reserve them in a Venue. After every 120 seconds, 
   system expire the seats held and make all seats holded for the customer will be available for next holding.
   If customer is not exisited in our system during ticket holding process, system will automatically create customer in system and then
   hold seats for him/her.

## Getting Started
 
 Clone the ticket-service project.
 
 ```
 git clone https://github.com/bhmalli93/ticket-service.git
 ```
 
 ### Installing
 
 * cd to the project root directory. 
 ```
 cd ticket-service
 ```
 * Run below maven install command to build ticket-service-0.0.1.jar in _**target**_ folder 
 
 ```
 $ mvn clean install
 ```
 
 ## Running the tests
 
 This application have unit tests(Junit) and user acceptance tests(Cucumber). Please run below command
 to run all of them
```
 $ mvn test
 ```
    -------------------------------------------------------
     T E S T S
    -------------------------------------------------------
    Running com.walmart.ticketservice.junit.TicketServiceJunitTest
    Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.102 sec
    Running com.walmart.ticketservice.cucumber.TicketServiceTest
    @myFeature
    Feature: I need to Hold and Book Ticket
    
      @myFeature @Scenario1
      Scenario: Ticket Service                                                 # src/test/resources/ticketservice.feature:4
        Then I should have 100 seats available                                 # TicketServiceStepDefs.I_should_have_100_seats(int)
        When hold 25 seats for customer hold_tickets@gmail.com                 # TicketServiceStepDefs.hold_25_seats_for_customer(Integer,String)
        Then seathold id should not be null                                    # TicketServiceStepDefs.seat_hold_id_should_not_be_null()
        Then num of seats held 25                                              # TicketServiceStepDefs.num_of_seats_hold(int)
        Then I should have remaining 75 seats available                        # TicketServiceStepDefs.I_should_have_85_seats(int)
        When reserve seats for customer invalid hold id 200 and junk@email.com # TicketServiceStepDefs.reserve_seats_for_invalid_hold_id(int,String)
        Then you should get an exception msg                                   # TicketServiceStepDefs.you_should_get_exception()
        When Book Seats For HoldId hold_tickets@gmail.com                      # TicketServiceStepDefs.book_seats_for_existing_hold_id(String)
        Then I should see status as reserved for seats                         # TicketServiceStepDefs.I_should_see_status_reserved()
        When I Hold seats for group booking                                    # TicketServiceStepDefs.hold_remaining_seats_for_customer()
        Then I should see status as reserved for seats for group               # TicketServiceStepDefs.I_should_see_status_reserved_for_group()
        Then remaining seats should be 45                                      # TicketServiceStepDefs.remaining_seats_should_be_0(int)
        When customer try to hold zero seats then seat hold should be null     # TicketServiceStepDefs.customer_try_to_hold_zero_seats()
    
    1 Scenarios (1 passed)
    13 Steps (13 passed)
    0m0.042s
    
    Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.258 sec
    
    Results :
    
    Tests run: 7, Failures: 0, Errors: 0, Skipped: 0

 

    