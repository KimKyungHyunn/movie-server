package study.movie.dto.ticket;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import study.movie.domain.movie.FilmRating;
import study.movie.domain.schedule.Seat;
import study.movie.domain.theater.ScreenFormat;
import study.movie.domain.ticket.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

@Data
public class ReserveTicketResponse {
    /**
     * 예매 번호
     */
    private String reserveNumber;

    /**
     * 영화 정보
     */
    private FilmRating filmRating;
    private String movieTitle;
    private ScreenFormat screenFormat;
    private String movieImage;

    /**
     * 극장 정보
     */
    private String theaterName;
    private String screenName;

    /**
     * 예매 날짜 정보
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-EEE", timezone = "Asia/Seoul")
    private LocalDate date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime endTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalTime startTime;

    /**
     * 예매 인원 수
     */
    private Integer reservedMemberCount;

    /**
     * 좌석 정보
     */
    private String seats;

    /**
     * 결제 타입
     */
    private String paymentType;

    public ReserveTicketResponse(Ticket ticket) {
        this.reserveNumber = ticket.getReserveNumber();
        this.filmRating = ticket.getMovie().getFilmRating();
        this.movieTitle = ticket.getMovie().getTitle();
        this.screenFormat = ticket.getFormat();
        this.movieImage = ticket.getMovie().getImage();
        this.theaterName = ticket.getTheaterName();
        this.screenName = ticket.getScreenName();
        this.date = ticket.getScreenTime().getStartDateTime().toLocalDate();
        this.startTime = ticket.getScreenTime().getStartDateTime().toLocalTime();
        this.endTime = ticket.getScreenTime().getEndDateTime().toLocalTime();
        this.reservedMemberCount = ticket.getReservedMemberCount();
        this.seats = ticket.getSeats().stream().map(Seat::seatToString).collect(Collectors.joining(","));
    }
}
