package com.example.skillforge.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillforge.R
import com.example.skillforge.adapter.CategoryAdapter
import com.example.skillforge.adapter.CourseAdapter
import com.example.skillforge.data.SelectedCourse
import com.example.skillforge.databinding.FragmentHomeBinding
import com.example.skillforge.viewmodel.SkillViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SkillViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeData()

        viewModel.fetchData()
    }

    private fun setupRecyclerViews() {

        binding.rvCategories.layoutManager =
            LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

        binding.rvCourses.layoutManager =
            LinearLayoutManager(requireContext())
    }

    private fun observeData() {

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility =
                if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.data.observe(viewLifecycleOwner) { response ->

            val categories = response.categories

            binding.rvCategories.adapter =
                CategoryAdapter(categories)

            val allCourses = categories.flatMap { it.courses }

            binding.rvCourses.adapter =
                CourseAdapter(allCourses) { course ->

                    // Save selected course
                    SelectedCourse.course = course

                    // Navigate to Course Detail
                    findNavController().navigate(
                        R.id.action_homeFragment_to_courseDetailFragment
                    )
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}