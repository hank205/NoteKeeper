package com.example.notekeeper

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class DataManagerTest {

    @Before
    fun setUp() {
        DataManager.notes.clear()
        DataManager.initializeCourses()
    }

    @After
    fun tearDown() {
        DataManager.notes.clear()
    }

    @Test
    fun addNote() {
        val course = DataManager.courses["course id 2"]
        val noteTitle = "test noteTitle"
        val noteText = "test noteText"

        val index = DataManager.addNote(course, noteTitle, noteText)
        val note = DataManager.notes[index]
        assertEquals(course, note.course)
        assertEquals(noteTitle, note.title)
        assertEquals(noteText, note.text)
    }

    @Test
    fun findSimilarNotes() {
        val course = DataManager.courses["course id 2"]!!
        val noteTitle = "test noteTitle"
        val noteText1 = "test noteText"
        val noteText2 = "test noteText2"

        val index1 = DataManager.addNote(course, noteTitle, noteText1)
        val index2 = DataManager.addNote(course, noteTitle, noteText2)

        val foundNote1 = DataManager.findNote(course, noteTitle, noteText1)
        val foundIndex1 = DataManager.notes.indexOf(foundNote1)
        assertEquals(index1, foundIndex1)

        val foundNote2 = DataManager.findNote(course, noteTitle, noteText2)
        val foundIndex2 = DataManager.notes.indexOf(foundNote2)
        assertEquals(index2, foundIndex2)
    }

}