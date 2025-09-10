# Übungsblatt: Objektorientierte Programmierung - Fortgeschrittene Konzepte
[Link to English version](./README_en.md)

In diesem Übungsblatt lernen Sie, objektorientierten Quellcode zur Lösung verschiedener Aufgaben zu entwickeln.

## Übung: Vererbung (`Vehicles` - Fahrzeuge)

### Aufgaben

Erstellen Sie eine Reihe von Klassen, um verschiedene Arten von Fahrzeugen darzustellen. Alle Fahrzeuge haben eine Marke (`make`), ein Modell (`model`), ein Jahr (`year`) und ein Gewicht (`weight`). 
Einige Fahrzeuge haben auch zusätzliche Eigenschaften. Zum Beispiel hat ein Auto (`car`) eine Anzahl von Türen (`doors`) und ein Lastwagen (`truck`) hat eine Ladungskapazität (`cargoCapacity`).
Verwenden Sie Vererbung, um eine Hierarchie von Fahrzeugklassen zu erstellen, wobei eine Basisklasse die gemeinsamen Attribute und Operationen darstellt und abgeleitete Klassen die spezifischen Arten von Fahrzeugen darstellen.

Implementieren Sie die Klassen im Paket `de.phl.programmingproject.vehicles`.

1. Erstellen Sie eine abstrakte Klasse namens `Vehicle` mit den folgenden Attributen und Operationen:
   - `make`: ein `String`, der die Marke des Fahrzeugs darstellt
   - `model`: ein `String`, der das Modell des Fahrzeugs darstellt
   - `year`: eine Ganzzahl (`int`), die das Herstellungsjahr des Fahrzeugs darstellt
   - `weight`: eine Dezimalzahl (`double`), die das Gewicht des Fahrzeugs in Kilogramm darstellt
   - einen Konstruktor zur Initialisierung dieser Attribute
   - abstrakte Operationen: `String getFuelType()` (Kraftstofftyp) und `int getMileage()` (Kilometerstand)

2. Erstellen Sie eine Unterklasse von `Vehicle` names `Car` mit den folgenden zusätzlichen Attributen und Operations:
   - `doors`: eine Ganzzahl (`int`), die die Anzahl von Türen des Autos angibt integer representing the number of doors on the car
   - ein Konstruktor, um die Attribute zu initialisieren
   - eine Implementierung der `String getFuelType()`-Operation, die "electrical" zurückgibt
   - eine Implementierung der `int getMileage()`-Operationen, die den Kilometerstand des Autos zurückgibt. Wählen Sie einen beliebigen hartkodierten Wert. of the car. Choose any hard coded value.

3. Erstellen Sie eine Unterklasse von `Vehicle` names `Truck` mit den folgenden zusätzlichen Attributen und Operations:
   - `cargoCapacity`: eine Dezimalzahl (`double`), die das maximal zulässige Gewicht der Ladung, die der Lkw befördern kann, in Kilogramm angibt
   - ein Konstruktor, um die Attribute zu initialisieren
   - eine Implementierung der `String getFuelType()`-Operation, die "diesel" zurückgibt
   - eine Implementierung der `int getMileage()`-Operationen, die den Kilometerstand des Lkws zurückgibt. Wählen Sie einen beliebigen hartkodierten Wert.

4. Erstellen Sie eine Unterklasse von `Car` names `SportsCar` mit den folgenden zusätzlichen Attributen und Operations:
   - `hasSpoiler`: ein boolescher Wert (`boolean`), der angibt, ob der Sportwagen einen Spoiler hat oder nicht
   - ein Konstruktor, um die Attribute zu initialisieren
   - eine Implementierung der Operation `int getMileage()`, die einen höheren Kilometerstand als die Elternklasse liefert

5. Implementieren Sie die `main` Operation in der Klasse `Main`, um Instanzen jedes Fahrzeugs zu erstellen und deren Kilometerstand und Kraftstoffart auszugeben. Sie können auch andere Attribute zusätzlich ausgeben.

## Übung: Generics und Collections (`ShoppingCart` - Einkaufswagen)

In dieser Übung üben Sie die Verwendung von Generics und Collections in Java, indem Sie einen Einkaufswagen für einen Online-Shop implementieren.
Implementieren Sie die Klassen im Paket `de.phl.programmingproject.shoppingcart`.

Erstellen Sie eine Klasse `ShoppingCart`, die einen Einkaufswagen für einen Online-Shop darstellt und Generics verwendet, um den Typ der Artikel zur Laufzeit festzulegen. Daher sollte die Klasse `T` als Typparameter auf Klassenebene unterstützen. Dieser Einkaufswagen sollte die folgenden Operationen unterstützen:

1. `void addItem(T item)`: Fügt den angegebenen Artikel zum Warenkorb hinzu.
2. `void removeItem(T item)`: Entfernt den angegebenen Artikel aus dem Warenkorb.
3. `void clearCart()`: Entfernt alle Artikel aus dem Warenkorb.
4. `int getCartSize()`: Gibt die Anzahl der Artikel im Warenkorb zurück.
5. `boolean contains(T item)`: Gibt `true` zurück, wenn der Warenkorb den angegebenen Artikel enthält.
6. `List<T> getCartItems()`: Gibt eine Liste mit allen Artikeln im Warenkorb zurück.
7. Zusätzlich zu diesen Operationen sollte `ShoppingCart` einen Konstruktor haben, der einen leeren Warenkorb erstellt.

Um Ihre Implementierung zu testen, implementieren Sie die `main` Operation, sodass sie Folgendes tut:

1. Erstellt eine Instanz der Klasse `ShoppingCart`, wobei ein Typparameter von `String` angegeben wird, d.h., `String` wird als Wert für den Typparameter bereitgestellt.
2. Erstellt mehrere `String`-Objekte und fügt sie dem Warenkorb hinzu.
3. Ruft die Operation `getCartSize` auf und gibt das Ergebnis aus.
4. Ruft die Operation `contains` auf, um zu überprüfen, ob ein bestimmter Artikel im Warenkorb ist, und gibt das Ergebnis aus.
5. Ruft die Operation `removeItem` auf, um einen bestimmten Artikel aus dem Warenkorb zu entfernen.
6. Ruft die Operation `getCartItems` auf, um eine Liste aller Artikel im Warenkorb zu erhalten und gibt das Ergebnis aus.
7. Wiederholt zusätzlich Aufgaben 1 bis 6 mit einer neuen `ShoppingCart`-Instanz und einem anderen Typ, der nicht `String` ist.


## Übung: Implementierung des Strategy Design Pattern in Java

In dieser Übung üben wir die Implementierung des Strategy Design Pattern in Java. Wir verwenden ein einfaches Beispiel eines Spiels, in dem ein&ast;e Spieler&ast;in zwischen verschiedenen Waffen wählen kann, um einen Feind anzugreifen. Der/die Spieler&ast;in kann zur Laufzeit zwischen den Waffen wechseln, und jede Waffe hat eine andere Angriffsstrategie.
Um diese Übung zu lösen, erstellen Sie ein neues Paket `de.phl.programmingproject.strategydesign` und implementieren Sie die Klassen darin.

### Aufgaben

1. Erstellen Sie ein `Interface` namens `AttackStrategy` mit einer einzigen Operation `attack()`. Diese Operation sollte keine Argumente nehmen und `void` zurückgeben.
2. Erstellen Sie drei Klassen, die das Interface `AttackStrategy` implementieren: `SwordAttackStrategy`, `AxeAttackStrategy` und `BowAttackStrategy`. Jede Klasse sollte ihre eigene Implementierung der Operation `attack()` haben. Zum Beispiel könnte `SwordAttackStrategy` einen Schwertangriff verwenden, während `BowAttackStrategy` einen Bogen und Pfeil verwenden könnte. Als Implementierung geben Sie den jeweiligen Angriff auf der Konsole aus, z.B. "Ein mächtiger Schwertangriff!" für die `SwordAttackStrategy`.
3. Erstellen Sie eine Klasse namens `Player`, die ein privates Attribut `strategy` vom Typ `AttackStrategy` und eine public Operation namens `attack()` hat. Diese Operation sollte einfach die Operation `attack()` des aktuellen Angriffsstrategie-Objekts aufrufen.
   * Implementieren Sie einen Konstruktor, der ein `AttackStrategy`-Objekt als Argument nimmt und das Attribut `strategy` auf diesen Wert setzt.
4. Fügen Sie der Klasse `Player` eine public Operation namens `changeAttackStrategy(final strategy AttackStrategy)` hinzu. Diese Operation sollte ein Argument vom Typ `AttackStrategy` nehmen und das Attribut auf diesen Wert setzen.
5. Erstellen Sie eine `Main`-Klasse. In der `main()`-Operation erstellen Sie ein `Player`-Objekt und setzen dessen Angriffsstrategie auf eine `SwordAttackStrategy`. Dann rufen Sie die Operation `attack()` des `Player`-Objekts auf. Überprüfen Sie, ob die richtige Angriffsstrategie verwendet wird.
6. Ändern Sie die Angriffsstrategie des `Player`-Objekts auf eine `AxeAttackStrategy`. Rufen Sie die Operation `attack()` erneut auf und überprüfen Sie, ob die richtige Angriffsstrategie verwendet wird.
7. Ändern Sie die Angriffsstrategie des `Player`-Objekts auf eine `BowAttackStrategy`. Rufen Sie die Operation `attack()` erneut auf und überprüfen Sie, ob die richtige Angriffsstrategie verwendet wird.
8. Erstellen Sie eine neue Klasse namens `Enemy` mit einer public Operation namens `takeDamage(int damage)`. Diese Operation sollte einfach eine Nachricht auf der Konsole ausgeben, die anzeigt, dass der Feind den angegebenen Schaden genommen hat.
9. Ändern Sie die Operation `attack()` der Klasse `Player`, um ein Argument vom Typ `Enemy` zu nehmen. Wenn die Operation `attack()` aufgerufen wird, sollte sie die Operation `takeDamage()` des `Enemy`-Objekts aufrufen und einen zufälligen Wert zwischen 1 und 10 als Schadensmenge übergeben.
10. Ändern Sie die `main`-Methode. Erstellen Sie ein `Enemy`-Objekt und übergeben Sie es der Operation `attack()` des `Player`-Objekts. Überprüfen Sie, ob der Feind Schaden nimmt und ob die richtige Angriffsstrategie verwendet wird.

Herzlichen Glückwunsch, Sie haben das Strategy Design Pattern in Java erfolgreich implementiert!
