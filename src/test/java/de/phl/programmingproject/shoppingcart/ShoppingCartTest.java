package de.phl.programmingproject.shoppingcart;

import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the ShoppingCart class.
 */
public class ShoppingCartTest {
    @Test
    void task_1_ShoppingCart_class_implemented() {
        /**
         *
         Create a class `ShoppingCart` which represents a shopping cart for an online store, using Generics to allow the type of items to be specified at runtime. Therefore, the class should support `T` as type parameter on the class level. This shopping cart should support the following operations:

         1. `void addItem(T item)`: Adds the specified item to the cart.
         2. `void removeItem(T item)`: Removes the specified item from the cart.
         3. `void clearCart()`: Removes all items from the cart.
         4. `int getCartSize()`: Returns the number of items in the cart.
         5. `boolean contains(T item)`: Returns true if the cart contains the specified item.
         6. `List<T> getCartItems()`: Returns a List containing all the items in the cart.
         7. In addition to these operations, `ShoppingCart` should have a constructor that creates an empty cart.

         */

        Class<?> shoppingCartClass = TestUtils.getClassForName("ShoppingCart", "de.phl.programmingproject.shoppingcart");

        TestUtils.assertClassHasFieldOfType(shoppingCartClass, "items", List.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "addItem", void.class, Object.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "removeItem", void.class, Object.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "clearCart", void.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "getCartSize", int.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "contains", boolean.class, Object.class);
        TestUtils.assertClassHasMethod(shoppingCartClass, "getCartItems", List.class);

        Constructor<?> constructor = TestUtils.getConstructor(shoppingCartClass);


        try {
            Constructor<String> stringConstructor = (Constructor<String>) shoppingCartClass.getConstructor();
            Object shoppingCart = stringConstructor.newInstance();
            for(int i=0; i < 5 ;i++){
                shoppingCartClass.getDeclaredMethod("addItem", Object.class).invoke(shoppingCart, "TestItem " + i);
            }
            assertEquals(5, shoppingCartClass.getDeclaredMethod("getCartSize").invoke(shoppingCart),
                    "The 'addItem' method does not work correctly.");

            shoppingCartClass.getDeclaredMethod("removeItem", Object.class).invoke(shoppingCart, "TestItem 0");
            assertEquals(4, shoppingCartClass.getDeclaredMethod("getCartSize").invoke(shoppingCart),
                    "The 'removeItem' method does not work correctly.");

            assertTrue(shoppingCartClass.getDeclaredMethod("contains", Object.class).invoke(shoppingCart, "TestItem 1").equals(true),
                    "The 'contains' method does not work correctly.");
            assertFalse(shoppingCartClass.getDeclaredMethod("contains", Object.class).invoke(shoppingCart, "TestItem 0").equals(true),
                    "The 'contains' method does not work correctly.");

        } catch (Exception e) {
            System.err.println(e);
            fail("Failed to create an instance of the ShoppingCart and work with it.");
        }
    }

    @Test
    void task_2_Main_implemented(){
        /**
         * 1. Creates an instance of the `ShoppingCart` class, specifying a type parameter of `String`, i.e., providing `String` as the value for the type parmeter.
         * 2. Creates several `String` objects and adds them to the cart.
         * 3. Calls the `getCartSize` operation and prints the result.
         * 4. Calls the `contains` operation to check if a specific item is in the cart and prints the result.
         * 5. Calls the `removeItem` operation to remove a specific item from the cart.
         * 6. Calls the `getCartItems` operation to get a list of all the items in the cart and prints the result.
         * 7. Additionally, repeat tasks 1 to 6 with a new `ShoppingCart` instance and another type which is not `String`.
         */

        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory( "main/java/de/phl/programmingproject/shoppingcart/Main.java");

        assertTrue(fileContent.contains("ShoppingCart<String>"), "The 'ShoppingCart' class is not instantiated with the type parameter 'String'.");
        assertTrue(fileContent.contains("addItem("), "The 'addItem' method is not called.");
        assertTrue(fileContent.contains("getCartSize()"), "The 'getCartSize' method is not called.");
        assertTrue(fileContent.contains("contains("), "The 'contains' method is not called.");
        assertTrue(fileContent.contains("removeItem("), "The 'removeItem' method is not called.");
        assertTrue(fileContent.contains("getCartItems()"), "The 'getCartItems' method is not called.");
    }
}
