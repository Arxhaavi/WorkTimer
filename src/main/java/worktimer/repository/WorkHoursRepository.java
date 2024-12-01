package worktimer.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import worktimer.entity.WorkTimes;

//Repository for work hours
public interface WorkHoursRepository extends CrudRepository<WorkTimes, Long> {
    // Create a way to find work hours by month
    List<WorkTimes> findByMonth(int month);
}
