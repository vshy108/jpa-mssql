package org.hopenghou.SpringBootApi.dto;

public class ListLocationDto {
  private int pageIndexZero = 0;
  private int pageSize = 10;
  private String columnName = "id";
  private boolean isAscending = true;

  public ListLocationDto(int pageIndexZero, int pageSize, String columnName, boolean isAscending) {
    this.pageIndexZero = pageIndexZero;
    this.pageSize = pageSize;
    this.columnName = columnName;
    this.isAscending = isAscending;
  }

  public String getColumnName() {
    return columnName;
  }

  public int getPageIndexZero() {
    return pageIndexZero;
  }

  public int getPageSize() {
    return pageSize;
  }

  public boolean getIsAscending() {
    return isAscending;
  }
}