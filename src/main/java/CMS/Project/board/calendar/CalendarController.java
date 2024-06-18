package CMS.Project.board.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> a707633a57c6ceb7873a6ccb6e145c4bf5ce6d76


<<<<<<< HEAD
@Controller
@RequestMapping("/Calendar")
public class CalendarController {
    @PostMapping("")
    public String getEventCalendar(){return "Calendar/Calendar";}
    @GetMapping
    public String viewCalendar(){return "Calendar/Calendar";}
    @PostMapping("/event")
    public @ResponseBody List<Map<String, Object>> GetCalendar(){return CalendarService.getCalendar();}
=======
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
>>>>>>> a707633a57c6ceb7873a6ccb6e145c4bf5ce6d76
}

