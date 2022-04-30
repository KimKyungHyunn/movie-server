package study.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.movie.domain.member.GenderType;
import study.movie.domain.member.Member;
import study.movie.domain.movie.*;
import study.movie.domain.theater.CityCode;
import study.movie.domain.theater.Screen;
import study.movie.domain.theater.ScreenFormat;
import study.movie.domain.theater.Theater;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static study.movie.domain.theater.ScreenFormat.*;
import static study.movie.domain.theater.ScreenFormat.PREMIUM;

@Component
@Transactional
@RequiredArgsConstructor
public class InitService {
    private final EntityManager em;

    public void initDB() {
        addTheaterScreen(
                "CGV 용산",
                CityCode.SEL,
                Arrays.asList("1관", "2관", "3관", "4관", "5관"),
                Arrays.asList(TWO_D, IMAX, FOUR_D_FLEX, SCREEN_X, PREMIUM));
        addTheaterScreen(
                "CGV 강남",
                CityCode.SEL,
                Arrays.asList("1관", "2관", "3관", "4관"),
                Arrays.asList(TWO_D, TWO_D, FOUR_D_FLEX, PREMIUM));
        addTheaterScreen(
                "CGV 수원",
                CityCode.KYG,
                Arrays.asList("1관", "2관", "3관", "4관", "5관"),
                Arrays.asList(TWO_D, TWO_D, TWO_D, SCREEN_X, PREMIUM));

        addMovies(
                Arrays.asList("영화1", "영화2", "영화3", "영화4", "영화5", "영화6"),
                Arrays.asList("감독1", "감독2", "감독3", "감독4", "감독5", "감독6"),
                Arrays.asList(
                        Arrays.asList(FilmFormat.TWO_D, FilmFormat.FOUR_D_FLEX, FilmFormat.IMAX),
                        Arrays.asList(FilmFormat.TWO_D, FilmFormat.SCREEN_X),
                        Arrays.asList(FilmFormat.TWO_D, FilmFormat.FOUR_D_FLEX, FilmFormat.IMAX, FilmFormat.SCREEN_X),
                        Arrays.asList(FilmFormat.TWO_D),
                        Arrays.asList(FilmFormat.TWO_D, FilmFormat.IMAX),
                        Arrays.asList(FilmFormat.TWO_D, FilmFormat.FOUR_D_FLEX)
                )
        );
    }

    public List<Screen> addTheaterScreen(String theaterName, CityCode city, List<String> screenNames, List<ScreenFormat> screenFormats) {
        List<Screen> savedScreens = new ArrayList<>();
        Theater theater = createTheater(theaterName, city);
        for (int i = 0; i < screenFormats.size(); i++) {
            savedScreens.add(registerScreen(screenNames.get(i), screenFormats.get(i), theater, 5, 5));
        }
        return savedScreens;
    }

    public List<Movie> addMovies(List<String> titles, List<String> directors, List<List<FilmFormat>> filmFormats) {
        List<Movie> savedMovies = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            savedMovies.add(createMovie(titles.get(i), directors.get(i), filmFormats.get(i)));
        }
        return savedMovies;
    }
    @Transactional
    public Theater createTheater(String theaterName, CityCode city) {
        Theater theater = Theater.builder()
                .name(theaterName)
                .city(city)
                .phone("0000-0000")
                .build();
        em.persist(theater);
        return theater;
    }

    @Transactional
    public Screen registerScreen(String screenName, ScreenFormat format, Theater theater, int maxCols, int maxRows) {
        return Screen.builder()
                .name(screenName)
                .format(format)
                .theater(theater)
                .maxCols(maxCols)
                .maxRows(maxRows)
                .build();
    }
    @Transactional
    public Movie createMovie(String title, String director, List<FilmFormat> formats) {
        Movie movie = Movie.builder()
                .title(title)
                .director(director)
                .actors(Arrays.asList("배우1", "배우2", "배우3"))
                .formats(formats)
                .filmRating(FilmRating.G_RATED)
                .genres(Arrays.asList(MovieGenre.values()[0], MovieGenre.values()[1]))
                .image(title + ".jpg")
                .info(title + " information")
                .nation("KR")
                .runningTime(160)
                .releaseDate(LocalDate.now().plusDays(10))
                .build();
        em.persist(movie);
        return movie;
    }
    @Transactional
    public Review writeReview(Movie movie) {
        Review review = Review.builder()
                .writer("홍길동")
                .comment("리뷰 내용")
                .score(10F)
                .movie(movie)
                .build();
        return review;
    }
    @Transactional
    public Member createMember() {
        Member member = Member.builder()
                .name("홍길동")
                .nickname("홍길동")
                .email("aaa@naver.com")
                .gender(GenderType.MALE)
                .birth(LocalDate.now())
                .build();
        em.persist(member);
        return member;
    }
}
