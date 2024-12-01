package worktimer.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import worktimer.entity.WorkTimes;
import worktimer.service.WorkTimesService;

@Controller
public class WorktimeController {

    @Autowired
    private WorkTimesService workTimesService;

    // Handle requests to the login page
    @GetMapping(value = { "/", "/login" })
    public String login() {
        return "login";
    }

    // Created a custom template for checking if it goes to error. Not needed
    // generally but this was created
    // for testing purposes
    @GetMapping("/error")
    public String handleError() {
        return "error";
    }

    // Handle requests to the home page
    @GetMapping("/home")
    public String home(Model model) {
        // Way to get current month
        int currentMonth = LocalDate.now().getMonthValue();

        // New worktimes object for the form
        model.addAttribute("workTimes", new WorkTimes());

        // Here we get the current month's work time entries for the list
        List<WorkTimes> workTimesList = workTimesService.getCurrentMonthWorkTimes(currentMonth);
        model.addAttribute("workTimesList", workTimesList);

        // Calculate the total hours worked in current month
        long totalWorkTime = workTimesList.stream().mapToLong(WorkTimes::getTotalHours).sum();
        model.addAttribute("totalWorkTime", totalWorkTime);

        return "home";
    }

    // Handling for saving the work times
    @PostMapping("/home")
    public String saveWorkTimes(@ModelAttribute WorkTimes workTimes) {
        workTimesService.saveWorkTimes(workTimes);
        // Redirect to update the list in real time
        return "redirect:../home";
    }

    // handle editing worktime data
    @GetMapping("/edit/{id}")
    public String editWorkTimes(@PathVariable("id") Long id, Model model) {
        WorkTimes workTimes = workTimesService.getWorkTimeById(id);
        model.addAttribute("workTimes", workTimes);
        return "editworktime";
    }

    // handle updating edited data in WorkTimes
    @PostMapping("/update")
    public String updateWorkTimes(@ModelAttribute WorkTimes workTimes) {
        workTimesService.saveWorkTimes(workTimes);
        // redirect back to the original page
        return "redirect:/home";
    }

    // Handle deletion
    @GetMapping("/delete/{id}")
    public String deleteWorkTimes(@PathVariable("id") Long id) {
        workTimesService.deleteWorkTimesById(id);
        return "redirect:../home";
    }
}