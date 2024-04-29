package org.hopenghou.SpringBootApi.entity;

import java.time.Instant;

import org.hopenghou.SpringBootApi.converter.SQLServerGeographyAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.geo.Point;

import jakarta.persistence.*;

@Entity
@Table(name = "location")
public class Location {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique=true)
  private String name;

  @Column(nullable = false)
  private boolean is_favourite = false;

  @Convert(converter = SQLServerGeographyAttributeConverter.class)
  @Column(columnDefinition = "geography", nullable = false)
  private Point center;

  @Convert(converter = SQLServerGeographyAttributeConverter.class)
  @Column(columnDefinition = "geography", nullable = false)
  private Point south_west;

  @Convert(converter = SQLServerGeographyAttributeConverter.class)
  @Column(columnDefinition = "geography", nullable = false)
  private Point north_east;

  @CreatedDate
  private Instant createdDate;

  @LastModifiedDate
  private Instant lastModifiedDate;

  // Getters
  public Long getId() {
    return id;
  }

  public String getName() {
      return name;
  }

  public boolean getIsFavourite() {
    return is_favourite;
  }

  public Point getCenter() {
    return center;
  }

  public Point getSouthWest() {
    return south_west;
  }

  public Point getNorthEast() {
    return north_east;
  }

  // Setters
  public void setName(String name) {
    this.name = name;
  }

  public void setIsFavourite(boolean isFavourite) {
    this.is_favourite = isFavourite;
  }

  public void setCenter(Point center) {
    this.center = center;
  }

  public void setSouthWest(Point southWest) {
    this.south_west = southWest;
  }

  public void setNorthEast(Point northEast) {
    this.north_east = northEast;
  }
}
