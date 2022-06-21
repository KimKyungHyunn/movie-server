package study.movie.domain.schedule.dto.condition;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import study.movie.domain.theater.entity.ScreenFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleBasicSearchCond {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate screenDate;

    private Long movieId;

    private ScreenFormat screenFormat;

    private Long theaterId;
}

