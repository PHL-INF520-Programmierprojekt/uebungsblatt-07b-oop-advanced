# Exercise Sheet
[Link to German Version](./README.md)

In this exercise sheet, you will learn to develop object-oriented source code to solve various tasks.

## Exercise: Inheritance (`Vehicles`)

### Tasks

Create a set of classes to represent different types of vehicles. All vehicles have a make (`make`), a model (`model`), a year (`year`), and a weight (`weight`). Some vehicles also have additional properties. For example, a car (`car`) has a number of doors (`doors`), and a truck (`truck`) has a cargo capacity (`cargoCapacity`). Use inheritance to create a hierarchy of vehicle classes, with a base class representing the common attributes and operations, and derived classes representing specific types of vehicles.

Implement the classes in the package `de.phl.programmingproject.vehicles`.

1. Create an abstract class called `Vehicle` with the following attributes and operations:
    - `make`: a `String` representing the make of the vehicle
    - `model`: a `String` representing the model of the vehicle
    - `year`: an integer (`int`) representing the year of manufacture
    - `weight`: a decimal number (`double`) representing the weight of the vehicle in kilograms
    - a constructor to initialize these attributes
    - abstract operations: `String getFuelType()` and `int getMileage()`

2. Create a subclass of `Vehicle` named `Car` with the following additional attributes and operations:
    - `doors`: an integer (`int`) representing the number of doors on the car
    - a constructor to initialize the attributes
    - an implementation of the `String getFuelType()` operation that returns "electrical"
    - an implementation of the `int getMileage()` operation that returns the mileage of the car. Choose any hard coded value.

3. Create a subclass of `Vehicle` named `Truck` with the following additional attributes and operations:
    - `cargoCapacity`: a decimal number (`double`) representing the maximum allowable weight of cargo the truck can carry, in kilograms
    - a constructor to initialize the attributes
    - an implementation of the `String getFuelType()` operation that returns "diesel"
    - an implementation of the `int getMileage()` operation that returns the mileage of the truck. Choose any hard coded value.

4. Create a subclass of `Car` named `SportsCar` with the following additional attributes and operations:
    - `hasSpoiler`: a boolean value (`boolean`) indicating whether the sports car has a spoiler or not
    - a constructor to initialize the attributes
    - an implementation of the `int getMileage()` operation that returns a higher mileage than the parent class

5. Implement the `main` operation in the class `Main` to create instances of each vehicle and output their mileage and fuel type. You may also output other attributes.

## Exercise: Generics and Collections (`ShoppingCart`)

In this exercise, you will practice using generics and collections in Java by implementing a shopping cart for an online shop.
Implement the classes in the package `de.phl.programmingproject.shoppingcart`.

Create a class `ShoppingCart` that represents a shopping cart for an online shop and uses generics to determine the type of items at runtime. Therefore, the class should support `T` as a type parameter at the class level. This shopping cart should support the following operations:

1. `void addItem(T item)`: Adds the specified item to the cart.
2. `void removeItem(T item)`: Removes the specified item from the cart.
3. `void clearCart()`: Removes all items from the cart.
4. `int getCartSize()`: Returns the number of items in the cart.
5. `boolean contains(T item)`: Returns `true` if the cart contains the specified item.
6. `List<T> getCartItems()`: Returns a list of all items in the cart.
7. In addition to these operations, `ShoppingCart` should have a constructor that creates an empty cart.

To test your implementation, implement the `main` operation so that it does the following:

1. Create an instance of the `ShoppingCart` class, specifying a type parameter of `String`, i.e., provide `String` as the value for the type parameter.
2. Create several `String` objects and add them to the cart.
3. Call the `getCartSize` operation and output the result.
4. Call the `contains` operation to check whether a specific item is in the cart and output the result.
5. Call the `removeItem` operation to remove a specific item from the cart.
6. Call the `getCartItems` operation to get a list of all items in the cart and output the result.
7. Additionally, repeat steps 1 to 6 with a new `ShoppingCart` instance and a different type that is not `String`.


## Exercise: Implementing the Strategy Design Pattern in Java

In this exercise, you will practice implementing the Strategy Design Pattern in Java. We use a simple example of a game in which a player can choose between different weapons to attack an enemy. The player can switch between weapons at runtime, and each weapon has a different attack strategy.
To solve this exercise, create a new package `de.phl.programmingproject.strategydesign` and implement the classes in it.

### Tasks

1. Create an `interface` called `AttackStrategy` with a single operation `attack()`. This operation should take no arguments and return `void`.
2. Create three classes that implement the `AttackStrategy` interface: `SwordAttackStrategy`, `AxeAttackStrategy`, and `BowAttackStrategy`. Each class should have its own implementation of the `attack()` operation. For example, `SwordAttackStrategy` could use a sword attack, while `BowAttackStrategy` could use a bow and arrow. As an implementation, print the respective attack to the console, e.g., "A mighty sword attack!" for the `SwordAttackStrategy`.
3. Create a class called `Player` that has a private attribute `strategy` of type `AttackStrategy` and a public operation called `attack()`. This operation should simply call the `attack()` operation of the current attack strategy object.
    * Implement a constructor that takes an `AttackStrategy` object as an argument and sets the `strategy` attribute to this value.
4. Add a public operation to the `Player` class called `changeAttackStrategy(final strategy AttackStrategy)`. This operation should take an argument of type `AttackStrategy` and set the attribute to this value.
5. Create a `Main` class. In the `main()` operation, create a `Player` object and set its attack strategy to a `SwordAttackStrategy`. Then call the `attack()` operation of the `Player` object. Check whether the correct attack strategy is used.
6. Change the attack strategy of the `Player` object to an `AxeAttackStrategy`. Call the `attack()` operation again and check whether the correct attack strategy is used.
7. Change the attack strategy of the `Player` object to a `BowAttackStrategy`. Call the `attack()` operation again and check whether the correct attack strategy is used.
8. Create a new class called `Enemy` with a public operation called `takeDamage(int damage)`. This operation should simply print a message to the console indicating that the enemy has taken the specified amount of damage.
9. Change the `attack()` operation of the `Player` class to take an argument of type `Enemy`. When the `attack()` operation is called, it should call the `takeDamage()` operation of the `Enemy` object and pass a random value between 1 and 10 as the amount of damage.
10. Change the `main` method. Create an `Enemy` object and pass it to the `attack()` operation of the `Player` object. Check whether the enemy takes damage and whether the correct attack strategy is used.

Congratulations, you have successfully implemented the Strategy Design Pattern in Java!
