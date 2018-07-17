package com.ticketservice.cucumber;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/ticketservice.feature"},
glue = {"com.ticketservice.cucumber"},
plugin = {"pretty", "html:target/cucumber"},
tags = {"@myFeature"})
public class TicketServiceTest {

}
