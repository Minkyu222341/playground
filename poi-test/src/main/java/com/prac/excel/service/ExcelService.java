package com.prac.excel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.prac.excel.CellEnum;
import com.prac.excel.domain.ExcelSheet;
import com.prac.excel.domain.dto.ExcelRequest;
import com.prac.excel.repository.CellRepository;
import com.prac.excel.repository.ExcelSheetRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.border.Border;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * packageName    : com.prac.excel.service
 * fileName       : ExcelService
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
@Service
@RequiredArgsConstructor
public class ExcelService {
    private final ExcelSheetRepository excelSheetRepository;


    public String downloadExcel(ExcelRequest request, HttpServletResponse response) throws IOException {
        // 엑셀 워크북 생성
        Workbook workbook = new XSSFWorkbook();

        // 시트 생성
        Sheet sheet = workbook.createSheet("셀 커스텀 테스트");

        // 헤더 행 생성
        Row headerRow = sheet.createRow(0);

        // 헤더 셀 스타일 설정
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerCellStyle.setFont(headerFont);

        // 데이터 조회 및 헤더 설정
        List<ExcelSheet> dataList = excelSheetRepository.findAll();

        int headerIndex = 0;
        for (String header : request.getHeaders()) {
            headerRow.createCell(headerIndex++).setCellValue(header);
        }


        // 필요한 필드 정보 가져오기
        List<Field> requiredFields = new ArrayList<>();
        for (String param : request.getParams()) {
            try {
                Field field = ExcelSheet.class.getDeclaredField(param);
                field.setAccessible(true);
                requiredFields.add(field);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        int rowNum = 1;
        for (ExcelSheet data : dataList) {
            Row row = sheet.createRow(rowNum++);
            int cellIndex = 0;
            for (Field field : requiredFields) {
                try {
                    Object fieldValue = field.get(data);
                    row.createCell(cellIndex++).setCellValue(fieldValue.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 셀 크기 조정
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) * 2); // 두 배로 넓히기
        }

        // 컨텐츠 타입과 파일 이름 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=cellTest.xlsx");

        // 엑셀 파일 출력
        workbook.write(response.getOutputStream());

        // 워크북 및 스트림 닫기
        workbook.close();

        return "ok";
    }


}
