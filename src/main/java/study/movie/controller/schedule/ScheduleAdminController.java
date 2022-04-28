package study.movie.controller.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.movie.dto.schedule.condition.ScheduleSearchCond;
import study.movie.dto.schedule.request.CreateScheduleRequest;
import study.movie.dto.schedule.response.CreateScheduleResponse;
import study.movie.dto.schedule.response.ScheduleResponse;
import study.movie.global.dto.Response;
import study.movie.global.page.PageableDTO;
import study.movie.service.schedule.ScheduleService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static study.movie.global.constants.ResponseMessage.CREATE_SCHEDULE;
import static study.movie.global.constants.ResponseMessage.READ_SCHEDULE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/v1/schedules")
public class ScheduleAdminController {
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.save(request);
        return Response.success(CREATED, CREATE_SCHEDULE, result);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestBody ScheduleSearchCond cond, PageableDTO pageableDTO) {
        Page<ScheduleResponse> result = scheduleService.search(cond, pageableDTO);
        return Response.success(READ_SCHEDULE, result);
    }
}