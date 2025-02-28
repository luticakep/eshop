package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CarControllerTest {

    @Mock
    private CarServiceImpl carService;

    @Mock
    private Model model;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCarPage() {
        String viewName = carController.createCarPage(model);
        verify(model).addAttribute(eq("car"), any(Car.class));
        assertEquals("CreateCar", viewName);
    }

    @Test
    void createCarPost() {
        Car car = new Car();
        String viewName = carController.createCarPost(car, model);
        verify(carService).create(car);
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void carListPage() {
        List<Car> mockCars = Arrays.asList(new Car(), new Car());
        when(carService.findAll()).thenReturn(mockCars);

        String viewName = carController.carListPage(model);
        verify(model).addAttribute("cars", mockCars);
        assertEquals("CarList", viewName);
    }

    @Test
    void editCarPageFound() {
        Car mockCar = new Car();
        when(carService.findById("123")).thenReturn(mockCar);

        String viewName = carController.editCarPage("123", model);
        verify(model).addAttribute("car", mockCar);
        assertEquals("EditCar", viewName);
    }

    @Test
    void editCarPost() {
        Car car = new Car();
        car.setCarId("123");

        String viewName = carController.editCarPost(car, model);
        verify(carService).update(eq("123"), any(Car.class));
        assertEquals("redirect:listCar", viewName);
    }

    @Test
    void deleteCar() {
        String viewName = carController.deleteCar("123");
        verify(carService).deleteCarById("123");
        assertEquals("redirect:listCar", viewName);
    }
}
