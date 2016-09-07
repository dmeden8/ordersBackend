package hr.ddcode.cafford.business;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "schedule")
public class ScheduledTasks {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(cron="${schedule.seconds} ${schedule.minutes} * * * MON-FRI")
    private void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}