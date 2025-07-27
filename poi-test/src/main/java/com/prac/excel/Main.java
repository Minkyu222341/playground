package com.prac.excel;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * packageName    : com.prac.excel
 * fileName       : Main
 * author         : MinKyu Park
 * date           : 2023-06-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-27        MinKyu Park       최초 생성
 */
public class Main {
  public static void main(String[] args) {
    // 현재 시간을 가져오고 한국 시간대로 변환
    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));

    // 특정 포맷으로 시간을 표시
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss");
    String formattedTime = now.format(formatter);

    System.out.println(formattedTime);
  }
}
