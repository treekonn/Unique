package com.kigya.bsutools.ui.edu

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.bsutools.R
import com.kigya.bsutools.databinding.FragmentEduBinding
import com.kigya.bsutools.databinding.FragmentScheduleBinding
import com.kigya.bsutools.utils.Constants

class SettingsFragment : Fragment(R.layout.fragment_edu) {
    private val viewBinding by viewBinding(FragmentEduBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {

        }
    }

}