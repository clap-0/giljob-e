package com.likelion.giljobe.controller;

import com.likelion.giljobe.dto.PolicyDetailResponseDto;
import com.likelion.giljobe.dto.PolicyListRequestDto;
import com.likelion.giljobe.dto.PolicyListResponseDto;
import com.likelion.giljobe.dto.PolicySaveRequestDto;
import com.likelion.giljobe.service.PolicyService;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Api(tags = "Policy", description = "정책 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    @ApiOperation(value = "정책 리스트 조회", notes = "검색어 및 필터링 조건을 사용해서 정책 리스트를 조회한다.\n" +
            "검색어나 필터링 조건들은 null 값이 될 수 있다.\n" +
            "쿼리스트링으로 전달할 것 (e.g. /api/policies?age=22&keyword=창업")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공")
    })
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "age", value = "연령", example="22")
    })
    public ResponseEntity<Page<PolicyListResponseDto>> getPolicylist(
            @Parameter(name = "requestDto", description = "검색어 및 필터링조건", in = QUERY, required = true)
                @Valid PolicyListRequestDto requestDto,
            @Parameter(name = "pageNumber", description = "조회할 페이지 (0부터 시작)", in = QUERY)
                @RequestParam(defaultValue = "0") Integer pageNumber,
            @Parameter(name = "pageSize", description = "한 페이지에 조회할 개수", in = QUERY)
                @RequestParam(defaultValue = "12") Integer pageSize
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<PolicyListResponseDto> response = this.policyService.findByFilter(requestDto, pageable);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "정책 상세 조회", notes = "정책 ID를 사용해서 해당 정책의 상세 정보를 조회한다.\n" +
            "Path variable로 전달할 것 (e.g. /api/policies/R2023050912273")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "주어진 정책 ID를 가진 정책이 없음")
    })
    @GetMapping("/{bizId}")
    public ResponseEntity<PolicyDetailResponseDto> getPolicy(
            @Parameter(name = "bizId", description = "정책 ID", in = PATH, required = true)
                @PathVariable(name = "bizId") String bizId
    ){
        try {
            PolicyDetailResponseDto response = this.policyService.findByBizId(bizId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "정책 추가", notes = "데이터베이스에 정책을 추가한다.\n" +
            "프론트에서 사용하지 않습니다!")
    @ApiResponses({
            @ApiResponse(code = 201, message = "저장 성공")
    })
    @PostMapping
    public ResponseEntity<Void> createPolicy(
            @Parameter(name = "policySaveRequestDto", description = "저장할 정책 정보", in = PATH, required = true)
                @RequestBody @Valid PolicySaveRequestDto policySaveRequestDto
    ) {
        this.policyService.save(policySaveRequestDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
