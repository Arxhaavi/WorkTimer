package worktimer.entity;

import java.time.LocalTime;
import java.time.Duration;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Entity for working times
@Entity
public class WorkTimes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int month;
    private int day;
    // Use localtime for specific hours/minutes
    private LocalTime startingHour;
    private LocalTime endingHour;

    public LocalTime getStartingHour() {
        return startingHour;
    }

    public void setStartingHour(LocalTime startingHour) {
        this.startingHour = startingHour;
    }

    public LocalTime getEndingHour() {
        return endingHour;
    }

    public void setEndingHour(LocalTime endingHour) {
        this.endingHour = endingHour;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Method to caluclate the time between starting and ending hours
    public long getTotalHours() {
        if (startingHour != null && endingHour != null) {
            return Duration.between(startingHour, endingHour).toHours();

        }

        return 0;
    }
}
