package CMS.Project.board.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/Calendar")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }
    @GetMapping
    public String getCalendarPage(Model model) {
        List<CalendarEvent> events = calendarService.getAllEvents();
        model.addAttribute("events", events);
        return "Calendar/Calendar";
    }

    @PostMapping("/add")
    @ResponseBody
    public CalendarEvent addEvent(@RequestBody CalendarEvent event) {
        return calendarService.saveEvent(event);
    }

//    @GetMapping("/Calendar")
//    public String getEventCalendar() {
//        return "Calendar/Calendar";
//    }
//
//    @GetMapping
//    public String viewCalendar() {
//        return "Calendar/Calendar";
//    }

}



