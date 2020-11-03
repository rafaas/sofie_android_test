package com.example.sofie_android_development_test.app.ui.fragment.form.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofie_android_development_test.domain.model.Task
import com.example.sofie_android_development_test.domain.repository.ITaskRepository
import com.example.sofie_android_development_test.resources.remote.api.request.TaskRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class AddViewModel(private val repository: ITaskRepository): ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScore = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _createdTask: MutableLiveData<Task> = MutableLiveData()
    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _error: MutableLiveData<Throwable> = MutableLiveData()

    val createdTask: LiveData<Task> get() = _createdTask

    val loading: LiveData<Boolean> get() = _loading

    val error: LiveData<Throwable> get() = _error

    fun create(taskRequest: TaskRequest){
        viewModelScore.launch {
            _loading.value = true
            try {
                _createdTask.value = repository.create(taskRequest)
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