package com.example.skillforge.ui.lesson

import android.os.Bundle
import android.widget.Toast
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.skillforge.adapter.LessonAdapter
import com.example.skillforge.data.SelectedCourse
import com.example.skillforge.data.SelectedLesson
import com.example.skillforge.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {

    private var _binding: FragmentLessonBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLessonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val course = SelectedCourse.course ?: return

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.imgVideo.load(course.thumbnailUrl)

        binding.tvCategory.text =
            "LESSON • ${course.title.uppercase()}"

        loadCurrentLesson()

        binding.rvLessons.layoutManager =
            LinearLayoutManager(requireContext())

        binding.rvLessons.adapter =
            LessonAdapter(
                lessons = course.lessons,
                selectedIndex = SelectedLesson.selectedIndex
            ) { lesson, position ->

                if (lesson.isFree) {

                    SelectedLesson.lesson = lesson
                    SelectedLesson.selectedIndex = position

                    loadCurrentLesson()

                    binding.rvLessons.adapter =
                        LessonAdapter(
                            lessons = course.lessons,
                            selectedIndex = SelectedLesson.selectedIndex,
                            onLessonClick = { clickedLesson, clickedPosition ->

                                if (clickedLesson.isFree) {

                                    SelectedLesson.lesson = clickedLesson
                                    SelectedLesson.selectedIndex = clickedPosition

                                    loadCurrentLesson()

                                } else {

                                    Toast.makeText(
                                        requireContext(),
                                        "Please enroll to access this lesson.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Please enroll to access this lesson.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        if (binding.tabLayout.tabCount == 0) {

            binding.tabLayout.addTab(
                binding.tabLayout.newTab().setText("Lessons")
            )

            binding.tabLayout.addTab(
                binding.tabLayout.newTab().setText("Notes")
            )

            binding.tabLayout.addTab(
                binding.tabLayout.newTab().setText("Resources")
            )
        }
    }

    private fun loadCurrentLesson() {

        val lesson = SelectedLesson.lesson ?: return

        binding.tvLessonTitle.text = lesson.title

        binding.tvLessonDescription.text =
            lesson.content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}