package org.hopenghou.SpringBootApi.dto;

public class UpdateLocationDto {
  private boolean isFavourite;

  // Constructors
  public UpdateLocationDto() {
  }

  public UpdateLocationDto(boolean isFavourite) {
    this.isFavourite = isFavourite;
  }
  
  public boolean getIsFavourite() {
    return isFavourite;
  }

  public void setFavourite(boolean isFavourite) {
    this.isFavourite = isFavourite;
  }
}
