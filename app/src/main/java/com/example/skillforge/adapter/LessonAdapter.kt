package com.example.skillforge.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skillforge.data.model.Lesson
import com.example.skillforge.databinding.ItemLessonBinding
import com.google.android.material.card.MaterialCardView

class LessonAdapter(
    private val lessons: List<Lesson>,
    private val selectedIndex: Int,
    private val onLessonClick: (Lesson, Int) -> Unit = { _, _ -> }
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(
        val binding: ItemLessonBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LessonViewHolder {

        val binding = ItemLessonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LessonViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: LessonViewHolder,
        position: Int
    ) {

        val lesson = lessons[position]

        holder.binding.apply {

            // Highlight selected lesson
            val card = root as MaterialCardView

            if (position == selectedIndex) {
                card.strokeWidth = 4
                card.strokeColor = Color.parseColor("#14B8A6")
            } else {
                card.strokeWidth = 1
                card.strokeColor = Color.parseColor("#E5E7EB")
            }

            tvLessonTitle.text = lesson.title
            tvDuration.text = "${lesson.durationMinutes} min"

            if (lesson.isFree) {
                tvFree.visibility = View.VISIBLE
                tvFree.text = "FREE"
                tvIcon.text = "▶"
                root.alpha = 1f
            } else {
                tvFree.visibility = View.GONE
                tvIcon.text = "🔒"
                root.alpha = 0.65f
            }

            root.setOnClickListener {

                onLessonClick(lesson, position)

            }
        }
    }

    override fun getItemCount(): Int = lessons.size
}