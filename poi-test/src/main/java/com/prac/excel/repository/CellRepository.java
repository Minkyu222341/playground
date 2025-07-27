package com.prac.excel.repository;

import com.prac.excel.domain.CellNum;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.prac.excel.repository
 * fileName       : CellRepository
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
public interface CellRepository extends JpaRepository<CellNum,Long> {
}
