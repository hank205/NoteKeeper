package com.example.notekeeper

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CreateNoteTest {
    // @Rule @JvmField
    @get:Rule
    val noteListActivity = ActivityTestRule(NoteListActivity::class.java)

    @Before
    fun setUp() {
//        DataManager.notes.clear()
//        DataManager.initializeCourses()
    }

    @After
    fun tearDown() {
//        DataManager.notes.clear()
    }

    @Test
    fun createNewNote() {
        val course = DataManager.courses["course id 2"]
        val noteTitle = "Test note title"
        val noteText = "This is the body of our test note"

        onView(withId(R.id.fab)).perform(click())

        // select spinner
        onView(withId(R.id.spinnerCourses)).perform(click())
        onData(allOf(instanceOf(CourseInfo::class.java), equalTo(course))).perform(click())
        // type text
        onView(withId(R.id.textNoteTitle)).perform(typeText(noteTitle))
        onView(withId(R.id.textNoteText)).perform(typeText(noteText), closeSoftKeyboard())

        pressBack()

        val newNote = DataManager.notes.last()
        assertEquals(course, newNote.course)
        assertEquals(noteTitle, newNote.title)
        assertEquals(noteText, newNote.text)
    }

}