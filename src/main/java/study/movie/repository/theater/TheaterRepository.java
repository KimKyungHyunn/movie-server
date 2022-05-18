package study.movie.repository.theater;

import org.springframework.data.jpa.repository.JpaRepository;
import study.movie.domain.theater.CityCode;
import study.movie.domain.theater.Theater;

import java.util.Arrays;

public interface TheaterRepository extends JpaRepository<Theater, Long>, TheaterRepositoryCustom {

}
