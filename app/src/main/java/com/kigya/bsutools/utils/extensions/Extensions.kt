package com.kigya.bsutools.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun <T> MutableLiveData<T>.share(): LiveData<T> = this

fun <T> Fragment.collectFlow(flow: Flow<T>, onCollect: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                onCollect(it)
            }
        }
    }
}