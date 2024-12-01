package worktimer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import worktimer.entity.WorkTimes;
import worktimer.repository.WorkHoursRepository;

//File for all the data manipulation methods
public class WorkTimesService {
    // Autowired for injecting the data from workhoursrepository
    @Autowired
    private WorkHoursRepository workHoursRepository;

    public List<WorkTimes> getCurrentMonthWorkTimes(int currentMonth) {
        // get workhours for current month
        return workHoursRepository.findByMonth(currentMonth);
    }

    // method for saving work times
    public void saveWorkTimes(WorkTimes workTimes) {
        workHoursRepository.save(workTimes);
    }

    // method for fetching a worktime entry by id
    public WorkTimes getWorkTimeById(Long id) {
        return workHoursRepository.findById(id).orElse(null);
    }

    // Method for deleting entry by id
    public void deleteWorkTimesById(Long id) {
        workHoursRepository.deleteById(id);
    }
}
