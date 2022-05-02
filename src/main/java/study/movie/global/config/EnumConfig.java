package study.movie.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.movie.domain.member.GenderType;
import study.movie.domain.movie.FilmFormat;
import study.movie.domain.movie.FilmRating;
import study.movie.domain.movie.MovieGenre;
import study.movie.domain.theater.CityCode;
import study.movie.domain.theater.ScreenFormat;
import study.movie.global.enumMapper.EnumMapper;
import study.movie.entitySearchStrategy.schedule.ScheduleMetaType;
import study.movie.entitySearchStrategy.ticket.TicketMetaType;

import static study.movie.global.constants.EnumClassConst.*;

@Configuration
public class EnumConfig {

    @Bean
    public EnumMapper enumMapper() {
        EnumMapper enumMapper = new EnumMapper();
        enumMapper.put(FILM_RATING.getClassName(), FilmRating.class);
        enumMapper.put(FILM_FORMAT.getClassName(), FilmFormat.class);
        enumMapper.put(MOVIE_GENRE.getClassName(), MovieGenre.class);
        enumMapper.put(CITY_CODE.getClassName(), CityCode.class);
        enumMapper.put(SCREEN_FORMAT.getClassName(), ScreenFormat.class);
        enumMapper.put(GENDER_TYPE.getClassName(), GenderType.class);
        enumMapper.put(SCHEDULE_SORT_TYPE.getClassName(), ScheduleMetaType.class);
        enumMapper.put(TICKET_SORT_TYPE.getClassName(), TicketMetaType.class);
        return enumMapper;
    }
}
