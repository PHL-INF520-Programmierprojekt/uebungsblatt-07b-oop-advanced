package de.phl.programmingproject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Utility class for tests.
 */
public class TestUtils {

    /**
     * The user's working directory.
     */
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");

    /**
     * Returns true if the given file exists in the root directory or in the src directory.
     * @param fileName
     * @return true if the given file exists in the root directory or in the src directory.
     */
    public static boolean fileExistsInRootOrSrcDirectory(String fileName) {
        return Files.exists(Paths.get(WORKING_DIRECTORY, fileName)) ||
                Files.exists(Paths.get(WORKING_DIRECTORY, "./src/"+fileName));
    }
    
    /**
     * Executes the given action and returns the output that was printed to the system out.
     *
     * @param actionThatPrintsToSystemOut
     * @return
     */
    public static String runActionAndGetSystemOut(Runnable actionThatPrintsToSystemOut) {
        // Save the original System.out
        PrintStream originalOut = System.out;

        // Create a stream to hold the output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(baos);

        // Set the new stream as the standard out
        System.setOut(newOut);

        // Call the action that prints to the system out
        actionThatPrintsToSystemOut.run();

        // Reset the standard out
        System.setOut(originalOut);

        String output = baos.toString();
        return output;
    }

    /**
     * Gets the content of the given file from the root or src directory.
     * @param fileName
     * @return
     */
    public static String getFileContentForFileInRootOrSrcDirectory(String fileName){
        if(!fileExistsInRootOrSrcDirectory(fileName)){
            fail(String.format("The file '%s' does not exist in the root (or './src') directory of the project.", fileName));
        }
        Path p1 = Paths.get(TestUtils.WORKING_DIRECTORY, fileName);
        Path p2 = Paths.get(TestUtils.WORKING_DIRECTORY, "./src/"+fileName);
        Path path = Files.exists(p1) ? p1 : p2;
        // load content of the file
        String txt;
        try {
            txt = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return txt;
    }

    /**
     * Returns the {@link Class<?>} with the given name in the given package. It fails if the class does not exist.
     * @param className
     * @param packageName
     * @return the {@link Class<?>} with the given name in the given package, or fails the test if the class does not exist
     */
    static Class<?> getClassForName(String className, String packageName) {
        try {
            return Class.forName(packageName + "." + className);
        } catch (ClassNotFoundException e) {
            fail(String.format("Class '%s' does not exist in the package '%s'", className, packageName));
        }
        return null;
    }

    /**
     * Returns the enum value with the given name. It fails if the enum does not contain the value.
     * @param enumClazz
     * @param name
     * @return the enum value with the given name, or fails the test if the enum does not contain the value
     */
    static Object getEnumValue(Class<?> enumClazz, String name) {
        try {
            return enumClazz.getDeclaredField(name).get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            fail(String.format("Enum '%s' does not contain the value '%s'", enumClazz.getSimpleName(), name));
        }
        return null;
    }

}
