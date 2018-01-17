package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TimeEntryHealthIndicator implements HealthIndicator{


    private final TimeEntryRepository repository;
    public static final int MAX_ALLOWED = 5;

    public TimeEntryHealthIndicator (TimeEntryRepository repository)
    {
        this.repository = repository;

    }





    @Override
    public Health health() {

        Health.Builder builder = new Health.Builder();
        List<TimeEntry> list = repository.list();
        if (list != null && list.size() < MAX_ALLOWED)
        {
            builder.up();
        }
        else
        {
            builder.down();
        }

        return builder.build();
    }



}
