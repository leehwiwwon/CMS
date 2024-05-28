package CMS.Project.board.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Calendar")
public class CalendarController {

    private CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/add")
    public CalendarEvent addEvent(@RequestBody CalendarEvent event) {
        return eventService.addEvent(event);
    }
    @Autowired
    private CalendarService eventService;


    @DeleteMapping("/delete/{eventId}")
    public void deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
    }
}

