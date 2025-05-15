package com.company.projectmanagement.view.timeentry;

import com.company.projectmanagement.entity.TimeEntry;
import com.company.projectmanagement.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.TimeSource;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

@Route(value = "time-entries/:id", layout = MainView.class)
@ViewController(id = "TimeEntry.detail")
@ViewDescriptor(path = "time-entry-detail-view.xml")
@EditedEntityContainer("timeEntryDc")
public class TimeEntryDetailView extends StandardDetailView<TimeEntry> {
    @Autowired
    private TimeSource timeSource;

    @Subscribe
    public void onInitEntity(final InitEntityEvent<TimeEntry> event) {
        final ZonedDateTime zonedDateTime = timeSource.now();
    }
    
}