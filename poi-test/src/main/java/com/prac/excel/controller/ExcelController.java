package com.prac.excel.controller;

import com.prac.excel.domain.ExcelSheet;
import com.prac.excel.domain.dto.ExcelRequest;
import com.prac.excel.domain.dto.ExcelResponse;
import com.prac.excel.service.ExcelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * packageName    : com.prac.excel.controller
 * fileName       : ExcelController
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
@Tag(name = "excel", description = "엑셀 추출 API")
@RestController
@RequiredArgsConstructor
public class ExcelController {
  private final ExcelService excelService;

  @PostMapping("/post")
  public ResponseEntity<String> addExcel(@RequestBody ExcelRequest request , HttpServletResponse response) throws IOException {
    return ResponseEntity.ok().body(excelService.downloadExcel(request,response));
  }

  @GetMapping
  public String hello() {
    return "docker-app";
  }

}
