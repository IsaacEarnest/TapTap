package com.example.osumania;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UpdateComboUnitTest extends GameActivity{

    @Test
    public void increment_comboCount_works() {
        int comboCount = 0;
        updateCombo(true);
        assertEquals(1, comboCount);
    }

    @Test
    public void do_not_increment_comboCount_works() {
        int comboCount = 0;
        updateCombo(false);
        assertEquals(0, comboCount);
    }
}