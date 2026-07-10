package com.example.skillforge.data

import com.example.skillforge.data.model.Lesson

object SelectedLesson {

    var lesson: Lesson? = null

    // Keeps track of which lesson is currently playing
    var selectedIndex: Int = 0
}