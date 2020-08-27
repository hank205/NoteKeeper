package com.example.notekeeper

data class CourseInfo(
    val courseId: String = "default courseId",
    val title: String = "default title"
) {
    override fun toString(): String {
        return title
    }
}

data class NoteInfo(
    var course: CourseInfo? = null,
    var title: String? = null,
    var text: String? = null
)