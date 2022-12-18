package com.kigya.unique.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kigya.unique.data.local.settings.AppSettings
import com.kigya.unique.utils.*
import com.kigya.unique.utils.extensions.share
import com.kigya.unique.utils.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class BaseViewModel(
    val appSettings: AppSettings,
    val logger: Logger
) : ViewModel() {

    private val _showErrorMessageResEvent = MutableLiveEvent<Int>()
    val showErrorMessageResEvent = _showErrorMessageResEvent.share()

    private val _showErrorMessageEvent = MutableLiveEvent<String>()
    val showErrorMessageEvent = _showErrorMessageEvent.share()

    fun CoroutineScope.safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch {
            try {
                block.invoke(this)
            } catch (e: ConnectionException) {
                logError(e)
                // _showErrorMessageResEvent.publishEvent(R.string.connection_error)
            } catch (e: Exception) {
                logError(e)
                // _showErrorMessageResEvent.publishEvent(R.string.internal_error)
            }
        }
    }

    private fun logError(e: Throwable) {
        logger.error(javaClass.simpleName, e)
    }

}