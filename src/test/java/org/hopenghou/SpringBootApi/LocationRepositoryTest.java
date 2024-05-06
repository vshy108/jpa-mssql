// NOTE: special data type cannot be created in h2 db
// package org.hopenghou.SpringBootApi;

// import org.hopenghou.SpringBootApi.entity.Location;
// import org.hopenghou.SpringBootApi.repository.LocationRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// // import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
// import org.springframework.data.geo.Point;

// import static org.junit.jupiter.api.Assertions.*;

// @DataJpaTest
// public class LocationRepositoryTest {

//   // Alternative for EntityManager
//   // Optional in this case, we can use bookRepository to do the same stuff
//   // @Autowired
//   // private TestEntityManager testEM;

//   @Autowired
//   private LocationRepository locationRepository;

//   @Test
//   public void testSave() {
//     Location l1 = new Location();
//     String name = "Location A";
//     l1.setName(name);
//     l1.setIsFavourite(true);
//     l1.setCenter(new Point(1, 2));
//     l1.setNorthEast(new Point(3, 4));
//     l1.setSouthWest(new Point(5, 6));

//     //testEM.persistAndFlush(b1); // the same
//     locationRepository.save(l1);

//     Long savedLocationID = l1.getId();

//     Location location = locationRepository.findById(savedLocationID).orElseThrow();
//     // Location location = testEM.find(Location.class, savedLocationID);

//     assertEquals(savedLocationID, location.getId());
//     assertEquals(name, location.getName());
//     assertEquals(new Point(1, 2), location.getCenter());
//     assertEquals(new Point(3, 4), location.getNorthEast());
//     assertEquals(new Point(5, 6), location.getSouthWest());
//   }
// }
