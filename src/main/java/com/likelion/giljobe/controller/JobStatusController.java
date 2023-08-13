package com.likelion.giljobe.controller;

import com.likelion.giljobe.domain.JobStatusEnum;
import com.likelion.giljobe.service.JobStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Api(tags = "JobStatus", description = "취업상태 목록 입력 용도로 만든 API 입니다. 프론트에서 쓸 일 없어요!")
@RestController
@RequestMapping("/api/jobStatus")
@RequiredArgsConstructor
public class JobStatusController {

    private final JobStatusService jobStatusService;


    @ApiIgnore
    @ApiOperation(value = "취업상태 저장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장 성공")
    })
    @PostMapping
    public ResponseEntity<Void> createJobStatus(
            @Parameter(name = "jobStatus", description = "학력", in = QUERY, required = true)
                @RequestParam JobStatusEnum jobStatus
    ){
        this.jobStatusService.save(jobStatus);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
