package com.kigya.unique.ui.schedule

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.unique.R
import com.kigya.unique.core.Resource
import com.kigya.unique.data.models.Row
import com.kigya.unique.databinding.FragmentScheduleBinding
import jp.wasabeef.recyclerview.animators.FadeInAnimator

class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private val viewBinding by viewBinding(FragmentScheduleBinding::bind)
    private val viewModel: ScheduleViewModel by activityViewModels()

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            setSpinnerSelectedIndex()
            viewModel.rows.observe(viewLifecycleOwner) { result ->
                viewModel.timetableList = result.data ?: emptyList()
                setProgressOptions(result)
            }
            weekSpinner.apply {
                setOnSpinnerItemSelectedListener(viewModel)
                setOnSpinnerOutsideTouchListener { _, _ -> dismiss() }
            }
            setupRecyclerView()
            setRecyclerViewAnimation()
        }
    }

    private fun FragmentScheduleBinding.setSpinnerSelectedIndex() {
        if (viewModel.currentDayIndex != null)
            weekSpinner.selectItemByIndex(viewModel.currentDayIndex!!)
    }

    private fun FragmentScheduleBinding.setupRecyclerView() {
        rvTimetable.apply {
            adapter = viewModel.rowAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun FragmentScheduleBinding.setProgressOptions(result: Resource<List<Row>>) {
        progressBar.isVisible =
            result is Resource.Loading && result.data.isNullOrEmpty()
        tvError.apply {
            isVisible = result is Resource.Error && result.data.isNullOrEmpty()
            text = result.error?.localizedMessage
        }
    }

    override fun onStop() {
        super.onStop()
        viewBinding.weekSpinner.dismiss()
        viewModel.clearUI()
    }


    private fun FragmentScheduleBinding.setRecyclerViewAnimation() {
        rvTimetable.itemAnimator =
            FadeInAnimator(OvershootInterpolator(0f)).apply {
                addDuration = 300
                removeDuration = 300
            }
    }
}