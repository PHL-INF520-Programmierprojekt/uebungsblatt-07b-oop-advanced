package de.phl.programmingproject.vehicles;

import de.phl.programmingproject.TestBase;
import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Vehicles exercise.
 */
public class VehiclesTest extends TestBase {

    @Test
    void task_1_Vehicle_class_with_properties_implemented(){
        /**
         * make: a string representing the make of the vehicle
         * model: a string representing the model of the vehicle
         * year: an integer representing the year the vehicle was made
         * weight: a double representing the weight of the vehicle in kilos
         * a constructor to initialize these attributes
         * abstract operations: String getFuelType() and int getMileage()
         */

        Class<?> vehicleClass = TestUtils.getClassForName("Vehicle", "de.phl.programmingproject.vehicles");

        assertTrue(java.lang.reflect.Modifier.isAbstract(vehicleClass.getModifiers()),
                "The Vehicle class is not abstract.");

        Map<String, Class<?>> expectedFields = new LinkedHashMap(){
            {
                put("make", String.class);
                put("model", String.class);
                put("year", int.class);
                put("weight", double.class);
            }
        };

        TestUtils.assertClassHasFieldsOfType(vehicleClass, expectedFields);

        TestUtils.assertClassHasMethod(vehicleClass, "getFuelType", String.class);
        TestUtils.assertClassHasMethod(vehicleClass, "getMileage", int.class);
    }

    @Test
    void task_2_Car_class_with_properties_implemented(){
        /**
         * Create a subclass of Vehicle called Car with the following additional attributes and operations:
         *
         * doors: an integer representing the number of doors on the car
         * a constructor to initialize these attributes
         * an implementation of the String getFuelType() operation that returns "electrical"
         * an implementation of the int getMileage() operation that returns the mileage of the car. Choose any hard coded value.
         */

        Class<?> carClass = TestUtils.getClassForName("Car", "de.phl.programmingproject.vehicles");

        Constructor<?> carConstructor = TestUtils.getConstructor(carClass, String.class, String.class, int.class, double.class, int.class);
        assertNotNull(carConstructor, "The Car class does not have a constructor with the required parameters 'Car(final String make, final String model, final int year, final double weight, final int doors)'.");

        TestUtils.assertClassHasFieldOfType(carClass, "doors", int.class);

        TestUtils.assertClassHasMethod(carClass, "getFuelType", String.class);
        TestUtils.assertClassHasMethod(carClass, "getMileage", int.class);

        assertEquals("electrical", TestUtils.invokeMethod(TestUtils.createInstance(carClass,
                "Nicola","Z", 2023, 1.337, 5), "getFuelType"));
    }

    @Test
    void task_3_Truck_class_with_properties_implemented(){
        /**
         * 3. Create a subclass of `Vehicle` called `Truck` with the following additional attributes and operations:
         *    - `cargoCapacity`: a double representing the maximum weight of cargo that the truck can carry in kilos
         *    - a constructor to initialize these attributes
         *    - an implementation of the `String getFuelType()` operation that returns "diesel"
         *    - an implementation of the `int getMileage()` operation that returns the mileage of the truck. Choose any hard coded value.
         */

        Class<?> truckClass = TestUtils.getClassForName("Truck", "de.phl.programmingproject.vehicles");

        Constructor<?> truckConstructor = TestUtils.getConstructor(truckClass, String.class, String.class, int.class, double.class, double.class);
        assertNotNull(truckConstructor, "The Truck class does not have a constructor with the required parameters 'Truck(final String make, final String model, final int year, final double weight, final double cargoCapacity)'.");

        TestUtils.assertClassHasFieldOfType(truckClass, "cargoCapacity", double.class);

        TestUtils.assertClassHasMethod(truckClass, "getFuelType", String.class);
        TestUtils.assertClassHasMethod(truckClass, "getMileage", int.class);

        Object truck = TestUtils.createInstance(truckClass, "Nicola", "Z", 2023, 1.337, 5.0);
        assertEquals("diesel", TestUtils.invokeMethod(truck, "getFuelType"));
    }

    @Test
    void task_4_SportsCarl_implemented(){
        /**
         * 4. Create a subclass of `Car` called `SportsCar` with the following additional attributes and operations:
         *
         *    - `hasSpoiler`: a boolean representing whether the sports car has a spoiler or not
         *    - a constructor to initialize these attributes
         *    - an implementation of the `int getMileage()` operation that returns a higher mileage value than the parent class
         */

        Class<?> sportsCarClass = TestUtils.getClassForName("SportsCar", "de.phl.programmingproject.vehicles");

        Constructor<?> sportsCarConstructor = TestUtils.getConstructor(sportsCarClass, String.class, String.class, int.class, double.class, int.class, boolean.class);
        assertNotNull(sportsCarConstructor, "The SportsCar class does not have a constructor with the required parameters 'SportsCar(final String make, final String model, final int year, final double weight, final int doors, final boolean hasSpoiler)'.");

        TestUtils.assertClassHasFieldOfType(sportsCarClass, "hasSpoiler", boolean.class);

        TestUtils.assertClassHasMethod(sportsCarClass, "getMileage", int.class);

        Object sportsCar = TestUtils.createInstance(sportsCarClass, "Nicola", "Z", 2023, 1.337, 5, true);
        Object car = TestUtils.createInstance(TestUtils.getClassForName("Car", "de.phl.programmingproject.vehicles"), "Nicola", "Z", 2023, 1.337, 5);

        assertTrue((int)TestUtils.invokeMethod(sportsCar, "getMileage") > (int)TestUtils.invokeMethod(car, "getMileage"), "The SportsCar class does not have a higher mileage than the Car class.");
    }

    @Test
    void task_5_Main_implemented(){
        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory("/src/main/java/de/phl/programmingproject/vehicles/Main.java");
        assertNotNull(fileContent, "The Main class is missing.");

        assertTrue(fileContent.contains("new Car"), "The Main class does not instantiates a Car.");
        assertTrue(fileContent.contains("new Truck"), "The Main class does not instantiates a Truck.");
        assertTrue(fileContent.contains("new SportsCar"), "The Main class does not instantiates a SportsCar.");
    }
}
