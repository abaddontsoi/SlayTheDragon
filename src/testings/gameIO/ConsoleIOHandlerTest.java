package testings.gameIO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gameIO.ConsoleIOHandler;

class ConsoleIOHandlerTest {

    private ConsoleIOHandler ioHandler;
    private final PrintStream standardOut = System.out;
    private final InputStream standardIn = System.in;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        ioHandler = new ConsoleIOHandler();
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
        System.setIn(standardIn);
    }

    @Test
    void testDisplayMessage() {
        String testMessage = "Test message";
        ioHandler.displayMessage(testMessage);
        assertEquals(testMessage, outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayMultipleMessages() {
        String message1 = "First message";
        String message2 = "Second message";
        
        ioHandler.displayMessage(message1);
        ioHandler.displayMessage(message2);
        
        String expected = message1 + System.lineSeparator() + message2;
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void testGetInput() {
        String simulatedInput = "Test input";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        ConsoleIOHandler newHandler = new ConsoleIOHandler(); // Create new instance with simulated input
        String result = newHandler.getInput();
        
        assertEquals(simulatedInput, result);
    }

    @Test
    void testGetMultipleInputs() {
        String simulatedInput = "First input\nSecond input\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        ConsoleIOHandler newHandler = new ConsoleIOHandler();
        String result1 = newHandler.getInput();
        String result2 = newHandler.getInput();
        
        assertEquals("First input", result1);
        assertEquals("Second input", result2);
    }

    @Test
    void testEmptyInput() {
        String simulatedInput = "\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        
        ConsoleIOHandler newHandler = new ConsoleIOHandler();
        String result = newHandler.getInput();
        
        assertEquals("", result);
    }

    @Test
    void testDisplayEmptyMessage() {
        ioHandler.displayMessage("");
        assertEquals("", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayNullMessage() {
        ioHandler.displayMessage(null);
        assertEquals("null", outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplaySpecialCharacters() {
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        ioHandler.displayMessage(specialChars);
        assertEquals(specialChars, outputStreamCaptor.toString().trim());
    }

    @Test
    void testDisplayMultilineMessage() {
        String multilineMessage = "Line 1\nLine 2\nLine 3";
        ioHandler.displayMessage(multilineMessage);
        assertEquals(multilineMessage, outputStreamCaptor.toString().trim());
    }
}