package com.example.notekeeper

// Singleton pattern
object DataManager {
    val courses = HashMap<String, CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        initializeCourses()
        initializeNotes()
    }

    fun addNote(course: CourseInfo? = null, title: String? = null, text: String? = null): Int {
        val note = NoteInfo(course, title, text)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(course: CourseInfo, title: String, text: String): NoteInfo? {
        for (note in notes) {
            if (course == note.course && title == note.title && text == note.text){
                return note
            }
        }
        return null
    }

    fun initializeCourses() {
        var course = CourseInfo()
        courses[course.courseId] = course

        course = CourseInfo("course id 1", "course title 1")
        courses[course.courseId] = course

        course = CourseInfo("course id 2", "course title 2")
        courses[course.courseId] = course

        course = CourseInfo("course id 3", "course title 3")
        courses[course.courseId] = course

        course = CourseInfo("course id 4", "course title 4")
        courses[course.courseId] = course
    }

    private fun initializeNotes() {
        var course = courses["default courseId"]!!
        var note = NoteInfo(course, "note title default", "note text default")
        notes.add(note)

        course = courses["course id 1"]!!
        note = NoteInfo(course, "note title 1", "note text 1")
        notes.add(note)

        course = courses["course id 2"]!!
        note = NoteInfo(course, "note title 2", "note text 2")
        notes.add(note)

        course = courses["course id 3"]!!
        note = NoteInfo(course, "note title 3", "note text 3")
        notes.add(note)

        course = courses["course id 4"]!!
        note = NoteInfo(course, "note title 4", "note text 4")
        notes.add(note)
    }

}