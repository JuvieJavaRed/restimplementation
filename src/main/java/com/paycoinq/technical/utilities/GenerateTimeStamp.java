package com.paycoinq.technical.utilities;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
public class GenerateTimeStamp {
    public static LocalDateTime retrieveTimeStamp(){
        LocalDateTime dateTime = LocalDateTime.now(); // Gets the current date and time
        return dateTime;
    }
}
