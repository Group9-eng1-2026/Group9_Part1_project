package io.github.group9.goosegame;

import io.github.group9.goosegame.Entities.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void TestPlayerMovement(){
        Player player = new Player(1,1);

        player.update(1, 1, 0, 100, 100);

        assertEquals(84f, player.pos.x, 0.001f, "Player didnt move as expected");
    }
    @Test
    public void TestEntityMovingKeepsCollision(){
        Player player = new Player(1,1);
        Player other = new Player(1, 1);

        assertTrue(player.intersects(other));

        other.update(1, 0, 1, 100, 100 );

        assertFalse(player.intersects(other));
    }
}
