package com.gallotti.appcafebarrestaurante.feature.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gallotti.appcafebarrestaurante.network.model.Store
import com.gallotti.appcafebarrestaurante.repository.StoreRepositoryContract
import com.gallotti.appcafebarrestaurante.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class StoreListViewModel(
    private val storeRepository: StoreRepositoryContract
): ViewModel() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    private val _state = MutableLiveData<ScreenState>()
    val state: LiveData<ScreenState>
        get() = _state

    private val _events = MutableLiveData<Event<SideEffect>>()
    val events: LiveData<Event<SideEffect>>
        get() = _events

    fun getListStore() {
        _state.postValue(ScreenState.Loading)
        scope.launch {
            val response = storeRepository.getStores(123.0,123.0)
            if (response.isSuccessful) {
                _state.postValue(
                    ScreenState.OnGetListStore(
                        response.body()!!
                    )
                )
            } else {
                val message = response.errorBody().toString()
                _state.postValue(
                    ScreenState.OnGetListStoreError(
                        message
                    )
                )
            }
        }
    }

    fun navigateToOptions(store: Store) {
            _events.postValue(Event(SideEffect.NavigateToDetailStore(store)))
    }

    sealed class SideEffect {
        data class NavigateToDetailStore(val store: Store) : SideEffect()
    }

    sealed class ScreenState{
        object Loading : ScreenState()

        data class OnGetListStore(val listStore: List<Store>) : ScreenState()
        data class OnGetListStoreError(val msg: String) : ScreenState()
    }
}