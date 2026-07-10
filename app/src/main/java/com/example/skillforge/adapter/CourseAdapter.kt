package com.example.skillforge.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.skillforge.R
import com.example.skillforge.data.model.Course
import com.example.skillforge.databinding.ItemCourseBinding

class CourseAdapter(
    private val courses: List<Course>,
    private val onCourseClick: (Course) -> Unit
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(
        val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseViewHolder {

        val binding = ItemCourseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int
    ) {

        val course = courses[position]

        holder.binding.apply {

            tvTitle.text = course.title
            tvLevel.text = course.level
            tvInstructor.text = course.instructor.name
            tvRating.text = "⭐ ${course.rating}"
            tvDuration.text = "⏰ ${course.durationHours} hrs"

            Log.d("IMAGE_URL", course.thumbnailUrl)

            imgCourse.load(course.thumbnailUrl) {
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }

            root.setOnClickListener {
                onCourseClick(course)
            }
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }
}