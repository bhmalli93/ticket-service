package com.ticketservice.constants;

import java.time.ZoneId;

public final class Constants {

    public static final long MAX_HOLDING_TIME_IN_SECONDS = 120;
    public static final ZoneId DEFAULT_TIME_ZONE = ZoneId.of("America/New_York");    
    public static final String EXCEPTION_MSG = "Invalid SeatHoldId or Invalid Customer Email. Both should be valid inorder to reserve seats.";

}
