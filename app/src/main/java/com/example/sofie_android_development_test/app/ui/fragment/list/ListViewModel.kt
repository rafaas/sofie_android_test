package com.example.sofie_android_development_test.app.ui.fragment.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofie_android_development_test.domain.model.Task
import com.example.sofie_android_development_test.domain.repository.ITaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ListViewModel(private val repository: ITaskRepository) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScore = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()

    val tasks: LiveData<List<Task>> get() = _tasks

    val loading: LiveData<Boolean> get() = _loading

    val error: LiveData<Throwable> get() = _error

    fun list(){
        viewModelScore.launch {
            _loading.value = true
            try {
                _tasks.value = repository.list()
            } catch (t: Throwable){
                _error.value = t
            } finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}