package com.prac.excel.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.prac.excel.domain
 * fileName       : CellNum
 * author         : MinKyu Park
 * date           : 2023-06-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-12        MinKyu Park       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "셀 헤더")
public class CellNum {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "식별자" , example = "1")
  private Long id;
  @Schema(description = "셀 헤더" , example = "셀 헤더")
  private String header;

  @Builder
  public CellNum(Long id, String header) {
    this.id = id;
    this.header = header;
  }
}
