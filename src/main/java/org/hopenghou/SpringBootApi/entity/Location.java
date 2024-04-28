package org.hopenghou.SpringBootApi.entity;

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

  @Column(columnDefinition = "geography", nullable = false)
  private Point center;

  @Column(columnDefinition = "geography", nullable = false)
  private Point south_west;

  @Column(columnDefinition = "geography", nullable = false)
  private Point north_east;

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

  // Setters
  public void setId(Long id) {
      this.id = id;
  }

  public void setName(String name) {
      this.name = name;
  }

  public void setIsFavourite(boolean is_favourite) {
    this.is_favourite = is_favourite;
  }
}
