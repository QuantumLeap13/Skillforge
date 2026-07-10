package com.example.skillforge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.skillforge.R
import com.example.skillforge.adapter.LessonAdapter
import com.example.skillforge.data.SelectedCourse
import com.example.skillforge.data.SelectedLesson
import com.example.skillforge.databinding.FragmentCourseDetailBinding

class CourseDetailFragment : Fragment() {

    private var _binding: FragmentCourseDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCourseDetailBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val course = SelectedCourse.course ?: return

        // Back Button
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        // Course Image
        binding.imgCourse.load(course.thumbnailUrl)

        // Course Info
        binding.tvTitle.text = course.title
        binding.tvLevel.text = course.level
        binding.tvInstructor.text = course.instructor.name
        binding.tvRating.text = "⭐ ${course.rating}"
        binding.tvDuration.text = "${course.durationHours} hrs"

        binding.tvDescription.text =
            "Master ${course.title} from beginner to advanced with practical lessons."

        binding.tvPrice.text = "Free"

        // Lessons RecyclerView
        binding.rvLessons.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvLessons.adapter =
            LessonAdapter(
                lessons = course.lessons,
                selectedIndex = SelectedLesson.selectedIndex
            ) { lesson, position ->

                if (lesson.isFree) {

                    // Save current lesson
                    SelectedLesson.lesson = lesson
                    SelectedLesson.selectedIndex = position

                    // Navigate
                    findNavController().navigate(
                        R.id.action_courseDetailFragment_to_lessonFragment
                    )

                } else {

                    Toast.makeText(
                        requireContext(),
                        "This lesson is locked. Please enroll first.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }

        // Enroll Button
        binding.btnEnroll.setOnClickListener {

            if (course.lessons.isNotEmpty()) {

                SelectedLesson.lesson = course.lessons.first()
                SelectedLesson.selectedIndex = 0

                findNavController().navigate(
                    R.id.action_courseDetailFragment_to_lessonFragment
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}