package com.prac.excel.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * packageName    : com.prac.excel
 * fileName       : ExcelSheet
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "셀 시트")
public class ExcelSheet {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "식별자" , example = "1")
  private Integer id;
  private String orderId;
  private String orderCustomId;
  private LocalDate closeTime;

  @Builder
  public ExcelSheet(Integer id, String orderId, String orderCustomId, LocalDate closeTime) {
    this.id = id;
    this.orderId = orderId;
    this.orderCustomId = orderCustomId;
    this.closeTime = closeTime;
  }
}
