package CMS.Project.board.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
public class CalendarController {
    @RequestMapping
    public String viewCalendar(){return "Calendar";}
    @GetMapping("/event")
    public @ResponseBody List<Map<String, Object>> GetCalendar(){return CalendarService.getCalendar();}
}
