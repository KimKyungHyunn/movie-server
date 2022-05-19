package study.movie.schedule.dto.condition;

import lombok.Data;
import study.movie.schedule.entity.ScheduleStatus;
import study.movie.theater.entity.ScreenFormat;
import study.movie.global.dto.DateRangeCond;

@Data
public class ScheduleSearchCond extends DateRangeCond {

    private String theaterName;
    private String movieTitle;
    private ScreenFormat screenFormat;
    private ScheduleStatus status;
}
