package study.movie.repository.schedule;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.movie.domain.schedule.Schedule;
import study.movie.domain.schedule.Seat;
import study.movie.dto.schedule.ScheduleSearchCond;
import study.movie.dto.schedule.UpdateSeatCond;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;
import static study.movie.domain.movie.QMovie.movie;
import static study.movie.domain.schedule.QSchedule.schedule;
import static study.movie.domain.schedule.QSeatEntity.seatEntity;
import static study.movie.domain.theater.QScreen.screen;
import static study.movie.domain.theater.QTheater.theater;


@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> searchSchedules(ScheduleSearchCond cond) {
        return queryFactory.selectFrom(schedule)
                .leftJoin(schedule.movie, movie)
                .leftJoin(schedule.screen, screen)
                .leftJoin(screen.theater, theater)
                .where(
                        movieTitleEq(cond.getMovieTitle()),
                        theaterNameEq(cond.getTheaterName()),
                        reserveDateEq(cond.getScreenDate())
                )
                .fetch();
    }

    @Override
    public void updateSeatStatus(UpdateSeatCond cond) {
        queryFactory.update(seatEntity)
                .set(seatEntity.status, cond.getStatus())
                .where(
                        seatsIn(cond.getSeats()),
                        scheduleEq(cond.getScheduleId()))
                .execute();
    }

    private BooleanExpression scheduleEq(Long scheduleId) {
        return scheduleId != null ? seatEntity.schedule.id.eq(scheduleId) : null;
    }

    private BooleanExpression seatsIn(List<Seat> seats) {
        return seats != null ? seatEntity.seat.in(seats) : null;
    }

    private BooleanExpression movieTitleEq(String movieTitle) {
        return hasText(movieTitle) ? movie.title.eq(movieTitle) : null;
    }

    private BooleanExpression theaterNameEq(String theaterName) {
        return hasText(theaterName) ? screen.theater.name.eq(theaterName) : null;
    }

    private BooleanExpression reserveDateEq(LocalDate date) {
        return date != null ? schedule.screenTime.startDateTime.between(
                date.atStartOfDay(),
                LocalDateTime.of(date, LocalTime.MAX).withNano(0)
        ) : null;
    }

}
