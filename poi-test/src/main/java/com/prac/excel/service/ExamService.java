package com.prac.excel.service;

import com.prac.excel.domain.ExcelSheet;
import com.prac.excel.domain.dto.ExcelResponse;
import com.prac.excel.repository.ExcelSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {
    private final ExcelSheetRepository excelSheetRepository;

    public List<ExcelResponse> findExcelList() {

        ArrayList<ExcelResponse> response = new ArrayList<>();

        List<ExcelSheet> excelList = excelSheetRepository.findAll();

        for (ExcelSheet sheet : excelList) {
            response.add(ExcelResponse.builder()
                    .sheetId(sheet.getId())
                    .orderId(sheet.getOrderId())
                    .orderCustomId(sheet.getOrderCustomId())
                    .build());
        }

        return response;
    }
}
