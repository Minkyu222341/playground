package com.prac.excel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.prac.excel.domain.ExcelSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName    : com.prac.excel.repository
 * fileName       : ExcelSheetRepository
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
public interface ExcelSheetRepository extends JpaRepository<ExcelSheet, Long> {
}
