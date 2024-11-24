package com.hopcape.common.domain.base.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S: BaseState, A: BaseAction, E: BaseEvent>(
    private val initialState: S
): ViewModel(){

    protected val _events = Channel<E>()
    val events get() = _events.receiveAsFlow()

    protected val _state = MutableStateFlow(initialState)
    val state get() = _state.asStateFlow()

    fun CoroutineScope.withDispatcher(dispatcher: CoroutineDispatcher = Dispatchers.IO,block: suspend () -> Unit){
        this.launch(dispatcher) {
            block()
        }
    }

    abstract fun onAction(action: A)

    protected fun sendEvent(event: E){
        _events.trySend(event)
    }


}

interface BaseEvent

interface BaseAction

interface BaseState