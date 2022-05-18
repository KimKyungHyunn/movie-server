package study.movie.repository.theater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import study.movie.domain.theater.Screen;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
    @Modifying
    @Transactional
    @Query("delete from Screen s where s.id = :id")
    void deleteByIdEqQuery(@Param("id") Long id);
}
