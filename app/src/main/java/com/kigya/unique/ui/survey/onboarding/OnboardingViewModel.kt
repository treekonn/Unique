package com.kigya.unique.ui.survey.onboarding

import com.kigya.unique.data.local.settings.AppSettings
import com.kigya.unique.ui.base.BaseViewModel
import com.kigya.unique.ui.survey.onboarding.OnboardingViewModel.OnboardingUiState.GesturePending
import com.kigya.unique.ui.survey.onboarding.OnboardingViewModel.OnboardingUiState.GestureRecieved
import com.kigya.unique.utils.constants.OnboardingConst
import com.kigya.unique.utils.extensions.collectFlow
import com.kigya.unique.utils.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.schedule

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    appSettings: AppSettings,
    logger: Logger
) : BaseViewModel(appSettings, logger) {

    private val _retainer: MutableStateFlow<ViewState> = MutableStateFlow(ViewState(GesturePending))
    val retainer = _retainer.asStateFlow()
    var isReady = false

    private val timer = Timer()

    fun performAfterDelay(action: () -> Unit) {
        timer.schedule(OnboardingConst.UI_WAITING_TIME) {
            if (_retainer.value.uiState == GesturePending) {
                handleIfPending {
                    changeReceive()
                    action()
                }
            }
        }
    }

    fun onUiTriggered(action: () -> Unit) {
        changeReceive()
        action()
    }

    fun handleIfPending(action: () -> Unit) {
        collectFlow(_retainer) {
            if (it.uiState == GesturePending) {
                action()
            }
        }
    }

    fun handleIfTriggered(
        viewState: ViewState,
        gestureHandled: () -> Unit
    ) {
        if (viewState.uiState == GestureRecieved) gestureHandled()
    }

    private fun changeReceive() {
        _retainer.value = ViewState(GestureRecieved)
        isReady = true
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    data class ViewState(
        val uiState: OnboardingUiState
    )

    sealed class OnboardingUiState {
        object GesturePending : OnboardingUiState()
        object GestureRecieved : OnboardingUiState()
    }
}