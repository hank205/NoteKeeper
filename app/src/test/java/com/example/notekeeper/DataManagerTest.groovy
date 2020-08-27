package com.example.notekeeper

class DataManagerGroovyTest extends groovy.util.GroovyTestCase {
    void testAddNote() {
        val course = DataManager.courses.get("course id 2")
        val noteTitle = "test noteTitle"
        val noteText = "test noteText"

        val index = DataManager.addNote(course, noteTitle, noteText)
        val note = DataManager.notes[index]
    }
}
