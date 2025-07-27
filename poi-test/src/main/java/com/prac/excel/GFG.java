package com.prac.excel;

/**
 * packageName    : com.prac.excel
 * fileName       : GFG
 * author         : MinKyu Park
 * date           : 2023-06-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-06-29        MinKyu Park       최초 생성
 */
class GFG {
  public static String camelToSnake(String str) {
    StringBuilder result = new StringBuilder();
    char c = str.charAt(0);
    result.append(Character.toLowerCase(c));
    for (int i = 1; i < str.length(); i++) {

      char ch = str.charAt(i);
      if (Character.isUpperCase(ch)) {
        result.append('_');
        result.append(Character.toLowerCase(ch));
      } else {
        result.append(ch);
      }
    }
    return result.toString();
  }


  public static void main(String args[]) {
    String str = "geeksForGeeksTestStringBuilderCamelCaseToSnakeCase";
    System.out.print(camelToSnake(str));
  }
}
