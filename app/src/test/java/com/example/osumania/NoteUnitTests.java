package com.example.osumania;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoteUnitTests {

    private int position = 64;
    private ArrayList<Integer> first = new ArrayList<>();
    private ArrayList<Integer> second = new ArrayList<>();
    private ArrayList<Integer> third = new ArrayList<>();
    private ArrayList<Integer> fourth = new ArrayList<>();

    private ArrayList<ArrayList<Integer>> allRows = new ArrayList<>();

    @Before
    public void populateArrayLists(){
        first.add(25);
        first.add(36);
        first.add(49);
        second.add(25);
        second.add(36);
        second.add(49);
        third.add(25);
        third.add(36);
        third.add(49);
        allRows.add(first);
        allRows.add(second);
        allRows.add(third);
        allRows.add(fourth);
    }

    //TODO I don't know how to populate this here from Notes.java ASK DR FERRER
    @Test
    public void toNextNoteTest(){
        populateArrayLists();
        Notes note = new Notes(allRows);
        assertEquals(25, note.toNextNote(position));
    }

    @Test
    public void getCurrentNoteTest(){
        populateArrayLists();
        Notes note = new Notes(allRows);
        assertEquals(25, note.getCurrentNote(position));
    }

    @Test
    public void hasNotesTest(){
        populateArrayLists();
        Notes notes = new Notes(allRows);
        assertFalse(notes.hasNotes());

    }

}
