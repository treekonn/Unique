package com.kigya.unique.ui.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.unique.R
import com.kigya.unique.databinding.FragmentSettingsBinding
import com.kigya.unique.utils.extensions.collectFlow

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewBinding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel: SettingsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            collectFlow(viewModel.pairState) { state ->
                courseChoises.check(viewModel.getCurrentCourseId(state.first))
                groupChoises.check(viewModel.getCurrentGroupId(state.second))
            }
            onCourseChosen()
            onGroupChosen()
            saveChanges.setOnClickListener {
                viewModel.collectData()
                findNavController().popBackStack()
            }
        }
    }

    private fun FragmentSettingsBinding.onGroupChosen() {
        groupChoises.setOnCheckedChangeListener { _, checkedId ->
            viewModel.performGroupIdActions(checkedId)
        }
    }


    private fun FragmentSettingsBinding.onCourseChosen() {
        courseChoises.setOnCheckedChangeListener { _, checkedId ->
            performCourseIdActions(checkedId)
        }
    }

    private fun FragmentSettingsBinding.performCourseIdActions(checkedId: Int) {
        viewModel.performCourseIdActions(checkedId)
        when (checkedId) {
            R.id.courseFirst -> {
                hideGroupTen()
                hideGroupEleven()
            }
            R.id.courseSecond, R.id.courseThird -> {
                showGroupTen()
                hideGroupEleven()
            }
            R.id.courseFourth -> {
                showGroupTen()
                showGroupEleven()
            }
            else -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun FragmentSettingsBinding.showGroupEleven() {
        groupEleven.isVisible = true
    }

    private fun FragmentSettingsBinding.hideGroupEleven() {
        groupEleven.isVisible = false
    }

    private fun FragmentSettingsBinding.showGroupTen() {
        groupTen.isVisible = true
    }

    private fun FragmentSettingsBinding.hideGroupTen() {
        groupTen.isVisible = false
    }

}