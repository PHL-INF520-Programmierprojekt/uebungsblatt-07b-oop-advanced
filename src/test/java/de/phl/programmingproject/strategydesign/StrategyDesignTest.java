package de.phl.programmingproject.strategydesign;

import de.phl.programmingproject.TestBase;
import de.phl.programmingproject.TestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Strategy Design Pattern exercise.
 */
public class StrategyDesignTest extends TestBase {
    /**
     * In this exercise, we will practice implementing the Strategy Design Pattern in Java. We will use a simple example of a game where a player can choose between different weapons to attack an enemy. The player can switch between weapons at runtime, and each weapon has a different attack strategy.
     * To solve this exercise, create a new package `de.phl.programmierprojekt.strategydesign` and implement the classes in it.
     * <p>
     * ### Tasks
     * <p>
     * 1. Create an interface called `AttackStrategy` with a single operation called `attack()`. This operation should take no arguments and return void.
     * <p>
     * 2. Create three classes that implement the `AttackStrategy` interface: `SwordAttackStrategy`, `AxeAttackStrategy`, and `BowAttackStrategy`. Each class should have its own implementation of the `attack()` operation. For example, `SwordAttackStrategy` might use a sword to attack, while `BowAttackStrategy` might use a bow and arrow. As implementation, print out the respective attack on the console, e.g., "A mighty sword attack!" for the `SwordAttackStrategy`.
     * <p>
     * 3. Create a class called `Player` that has a private attribute of type `AttackStrategy` and a public operation called `attack()`. This operation should simply call the `attack()` operation of the current attack strategy object.
     * <p>
     * 4. Add a public operation to the `Player` class called `changeAttackStrategy(final strategy AttackStrategy)`. This operation should take an argument of type `AttackStrategy` and set the attribute to this value.
     * <p>
     * 5. In the `main()` operation, create a `Player` object and set its attack strategy to a `SwordAttackStrategy`. Then, call the `attack()` operation of the `Player` object. Verify that the correct attack strategy is used.
     * <p>
     * 6. Change the attack strategy of the `Player` object to an `AxeAttackStrategy`. Call the `attack()` operation again and verify that the correct attack strategy is used.
     * <p>
     * 7. Change the attack strategy of the `Player` object to a `BowAttackStrategy`. Call the `attack()` operation again and verify that the correct attack strategy is used.
     * <p>
     * 8. Create a new class called `Enemy` with a public operation called `takeDamage(int damage)`. This operation should simply print a message to the console indicating that the enemy has taken the given amount of damage.
     * <p>
     * 9. Modify the `attack()` operation of the `Player` class to take an argument of type `Enemy`. When the `attack()` operation is called, it should call the `takeDamage()` operation of the `Enemy` object, passing in a random value between 1 and 10 as the amount of damage.
     * <p>
     * 10. Create a `Main` class. In the `main()` operation, create an `Enemy` object and pass it to the `attack()` operation of the `Player` object. Verify that the enemy takes damage, and that the correct attack strategy is used.
     * <p>
     * Congratulations, you have successfully implemented the Strategy Design Pattern in Java!
     */

    Class<?> getAttackStrategyInterface() {
        return this.getClass("AttackStrategy");
    }

    @Test
    void task_1_AttackStrategy_interface_implemented() {
        Class<?> attackStrategyInterface = getAttackStrategyInterface();
        assertTrue(attackStrategyInterface.isInterface());
        TestUtils.assertClassHasMethod(attackStrategyInterface, "attack", void.class);
    }

    @Test
    void task_2_attack_strategies_implemented() {
        Class<?> swordAttackStrategyClass = getClass("SwordAttackStrategy");
        Class<?> axeAttackStrategyClass = getClass("AxeAttackStrategy");
        Class<?> bowAttackStrategyClass = getClass("BowAttackStrategy");
        Class<?> attackStrategyInterface = getAttackStrategyInterface();
        assertTrue(attackStrategyInterface.isAssignableFrom(swordAttackStrategyClass));
        assertTrue(attackStrategyInterface.isAssignableFrom(axeAttackStrategyClass));
        assertTrue(attackStrategyInterface.isAssignableFrom(bowAttackStrategyClass));

    }

    @Test
    void task_3_Player_class_implemented() {
        Class<?> playerClass = getClass("Player");

        TestUtils.assertClassHasFieldOfType(playerClass, "strategy", getAttackStrategyInterface());
    }

    @Test
    void task_4_Player_implements_ChangeAttackStrategy() throws IllegalAccessException {
        Class<?> playerClass = getClass("Player");
        Class<?> attackStrategyInterface = getAttackStrategyInterface();
        TestUtils.assertClassHasMethod(playerClass, "changeAttackStrategy", void.class, attackStrategyInterface);

        Method changeAttackStrategy = TestUtils.getMethod(playerClass, "changeAttackStrategy", attackStrategyInterface);

        Field strategy = TestUtils.getField(playerClass, "strategy");

        Constructor<?> constructor = TestUtils.getConstructor(playerClass, attackStrategyInterface);

        Object swordAttackStrategy = TestUtils.createInstance(getClass("SwordAttackStrategy"));

        Object player = null;
        try {
            player = constructor.newInstance(swordAttackStrategy);
        } catch (Exception e) {
            System.err.println(e);
            fail("Failed to create instance of Player");
        }


        assertEquals(swordAttackStrategy, strategy.get(player));

        Object bowAttackStrategy = TestUtils.createInstance(getClass("BowAttackStrategy"));
        try {
            changeAttackStrategy.invoke(player, bowAttackStrategy);
        } catch (InvocationTargetException e) {
            System.err.println(e);
            fail("Failed to invoke changeAttackStrategy");
        }

        assertEquals(bowAttackStrategy, strategy.get(player));
    }

    @Test
    void task_5_6_7_Main_implemented() {
        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory("main/java/de/phl/programmingproject/strategydesign/Main.java");

        assertTrue(fileContent.contains("new Player("),
                "The 'Player' object is not created in the 'main' method of the 'Main' file.");

        assertTrue(fileContent.contains(".attack("),
                "The 'attack' method of the 'Player' object is not called in the 'main' method of the 'Main' file.");

        assertTrue(fileContent.contains(".changeAttackStrategy(new AxeAttackStrategy());"),
                "The 'changeAttackStrategy' method with 'new AxeAttackStrategy()' of the 'Player' object is not called in the 'main' method of the 'Main' file.");

        assertTrue(fileContent.contains(".changeAttackStrategy(new BowAttackStrategy());"),
                "The 'changeAttackStrategy' method with 'new BowAttackStrategy()' of the 'Player' object is not called in the 'main' method of the 'Main' file.");
    }

    @Test
    void task_8_Enemy_class_implemented() {
        Class<?> enemyClass = getClass("Enemy");

        TestUtils.assertClassHasMethod(enemyClass, "takeDamage", void.class, int.class);

        Object enemy = TestUtils.createInstance(enemyClass);
        String output = TestUtils.runActionAndGetSystemOut(() -> {
            try {
                TestUtils.invokeMethod(enemy, "takeDamage", 5);
            } catch (Exception e) {
                System.err.println(e);
                fail("Failed to invoke takeDamage");
            }
        });
        assertTrue(output.contains("5"), "The 'takeDamage' method of the 'Enemy' class does not print the correct damage value to the console.");
    }

    @Test
    void task_9_attack_method_modified() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> playerClass = getClass("Player");
        Class<?> enemyClass = getClass("Enemy");

        TestUtils.assertClassHasMethod(playerClass, "attack", void.class, enemyClass);

        Object player = playerClass.getConstructor(getAttackStrategyInterface()).newInstance(TestUtils.createInstance(getClass("SwordAttackStrategy")));
        Object enemy = TestUtils.createInstance(enemyClass);
        String output = TestUtils.runActionAndGetSystemOut(() -> {
            try {
                TestUtils.invokeMethod(player, "attack", enemy);
            } catch (Exception e) {
                System.err.println(e);
                fail("Failed to invoke attack");
            }
        });
        assertTrue(output.contains("damage"), "The 'takeDamage' method of the 'Enemy' class does not print the correct attack value to the console.");
    }

    @Test
    void task_10_Main_modified() {
        String fileContent = TestUtils.getFileContentForFileInRootOrSrcDirectory("main/java/de/phl/programmingproject/strategydesign/Main.java");

        assertTrue(fileContent.contains("new Enemy()"),
                "The 'Enemy' object is not created in the 'main' method of the 'Main' file.");

        Pattern pattern = Pattern.compile("attack\\([aA-zZ]*\\);");
        for (String s : fileContent.split("\n")) {
            if (pattern.matcher(s).find()){
                return;
            }
        }
        fail("The 'attack(Enemy enemy)' method of the 'Player' object is not called in the 'main' method of the 'Main' file.");;
    }

}
