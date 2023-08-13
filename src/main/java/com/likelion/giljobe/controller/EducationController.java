package com.likelion.giljobe.controller;

import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.service.EducationService;
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

@Api(tags = "Education", description = "학력 목록 입력 용도로 만든 API 입니다. 프론트에서 쓸 일 없어요!")
@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @ApiIgnore
    @ApiOperation(value = "학력 저장")
    @ApiResponses({
            @ApiResponse(code = 200, message = "저장 성공")
    })
    @PostMapping
    public ResponseEntity<Void> createEducation(
            @Parameter(name = "education", description = "학력", in = QUERY, required = true)
                @RequestParam EducationEnum education
    ){
        this.educationService.save(education);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
