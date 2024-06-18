package CMS.Project.board.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Calendar")
public class CalendarController {
    @PostMapping("")
    public String getEventCalendar(){return "Calendar/Calendar";}
    @GetMapping
    public String viewCalendar(){return "Calendar/Calendar";}
    @PostMapping("/event")
    public @ResponseBody List<Map<String, Object>> GetCalendar(){return CalendarService.getCalendar();}
}
