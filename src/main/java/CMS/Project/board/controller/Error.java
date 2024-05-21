package CMS.Project.board.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class Error implements ErrorController {

    private final String ERROR_404_PAGE_PATH = "error/404";
    private final String ERROR_500_PAGE_PATH = "error/500";
    private final String ERROR_ETC_PAGE_PATH = "error/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        HttpStatus httpStatus = HttpStatus.valueOf(Integer.valueOf(status.toString()));

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("code", status.toString());
                model.addAttribute("msg", httpStatus.getReasonPhrase());
                model.addAttribute("timeStamp", new Date());
                return ERROR_404_PAGE_PATH;
            }
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return ERROR_500_PAGE_PATH;
            }
        }

        return ERROR_ETC_PAGE_PATH;
    }

}
