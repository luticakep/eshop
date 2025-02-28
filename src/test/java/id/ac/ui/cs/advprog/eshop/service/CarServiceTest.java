package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        List<String> carIds = new ArrayList<>();
        carRepository.findAll().forEachRemaining(c -> carIds.add(c.getCarId()));

        // Delete all existing cars before each test
        for (String id : carIds) {
            carRepository.delete(id);
        }
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setCarId("car12321");
        car.setCarName("Test Car");
        car.setCarColor("Black");
        car.setCarQuantity(5);

        Car createdCar = carService.create(car);

        assertNotNull(createdCar);
        assertEquals("car12321", createdCar.getCarId());
        assertEquals("Test Car", createdCar.getCarName());
        assertEquals("Black", createdCar.getCarColor());
        assertEquals(5, createdCar.getCarQuantity());
    }

    @Test
    void findByIdNonExisting() {
        Car found = carService.findById("non-existent");
        assertNull(found);
    }

    @Test
    void findAllCars() {
        // Clear repository
        List<String> carIds = new ArrayList<>();
        carRepository.findAll().forEachRemaining(c -> carIds.add(c.getCarId()));
        carIds.forEach(carRepository::delete);

        Car car1 = new Car();
        car1.setCarId("c1");
        car1.setCarName("Car One");
        car1.setCarColor("Blue");
        car1.setCarQuantity(10);
        carService.create(car1);

        Car car2 = new Car();
        car2.setCarId("c2");
        car2.setCarName("Car Two");
        car2.setCarColor("Black");
        car2.setCarQuantity(15);
        carService.create(car2);

        List<Car> cars = carService.findAll();
        assertNotNull(cars);
        assertEquals(2, cars.size(), "Should find exactly 2 cars");
        assertTrue(cars.stream().anyMatch(c -> "c1".equals(c.getCarId())));
        assertTrue(cars.stream().anyMatch(c -> "c2".equals(c.getCarId())));
    }

    @Test
    void editCar() {
        Car car = new Car();
        car.setCarId("c1");
        car.setCarName("First Car");
        car.setCarColor("Green");
        car.setCarQuantity(5);
        carService.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarId("c1");
        updatedCar.setCarName("Updated Car");
        updatedCar.setCarColor("Yellow");
        updatedCar.setCarQuantity(20);

        carService.update("c1", updatedCar);
        Car result = carService.findById("c1");

        assertNotNull(result);
        assertEquals("Updated Car", result.getCarName());
        assertEquals("Yellow", result.getCarColor());
        assertEquals(20, result.getCarQuantity());
    }

    @Test
    void deleteCar() {
        Car car = new Car();
        car.setCarId("c1");
        car.setCarName("To Be Deleted");
        car.setCarColor("White");
        car.setCarQuantity(2);
        carService.create(car);

        assertNotNull(carService.findById("c1"));
        carService.deleteCarById("c1");
        assertNull(carService.findById("c1"));
    }

    @Test
    void findByIdExisting() {
        List<String> carIds = new ArrayList<>();
        carRepository.findAll().forEachRemaining(c -> carIds.add(c.getCarId()));
        carIds.forEach(carRepository::delete);

        Car car = new Car();
        car.setCarId("c1");
        car.setCarName("Existing Car");
        car.setCarColor("Silver");
        car.setCarQuantity(90);
        carService.create(car);

        Car foundCar = carService.findById("c1");
        assertNotNull(foundCar);
        assertEquals("c1", foundCar.getCarId(), "Car ID should match");
        assertEquals("Existing Car", foundCar.getCarName(), "Car name should match");
        assertEquals("Silver", foundCar.getCarColor(), "Car color should match");
        assertEquals(90, foundCar.getCarQuantity(), "Car quantity should match");
    }
}
