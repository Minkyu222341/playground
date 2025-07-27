package com.prac.excel.domain.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * packageName    : com.prac.excel.domain.dto
 * fileName       : ExcelResponse
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
@Getter
@Builder
public class ExcelResponse {
    private Integer sheetId;
    private String orderId;
    private String orderCustomId;
}
