package worktimer.worktime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import worktimer.configuration.SecurityConfig;

@SpringBootApplication
@Import(SecurityConfig.class)
public class WorktimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorktimeApplication.class, args);
	}

}
