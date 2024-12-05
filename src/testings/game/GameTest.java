package testings.game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Game;

class GameTest {
    
    private Game game;
    
    @BeforeEach
    void setUp() throws Exception {                
        game = Game.getInstance();
    }
    
    @Test
    void testGame() {
    	assertDoesNotThrow(() -> 
    	game.startGame()
    	);
    }
    

}