package com.example.sofie_android_development_test.app.ui.fragment.form

import android.content.Context
import androidx.lifecycle.*
import com.example.sofie_android_development_test.app.util.hasMinChars
import com.example.sofie_android_development_test.app.util.isEmailValid

class FormViewModel(val context: Context) : ViewModel() {

    private val _email : MutableLiveData<String> = MutableLiveData()
    private val _taskName : MutableLiveData<String> = MutableLiveData()
    private val _taskDescription : MutableLiveData<String> = MutableLiveData()
    private val _hasErrors: MediatorLiveData<Boolean> = MediatorLiveData()
    
    init {
        _hasErrors.addSource(_email, this::formStatus)
        _hasErrors.addSource(_taskName, this::formStatus)
        _hasErrors.addSource(_taskDescription, this::formStatus)
        _hasErrors.value = false
    }

    @Suppress("UNUSED_PARAMETER")
    private fun formStatus(value: String) {
        val isEmailValid= isEmailValid(_email.value)
        val isTaskNameValid= hasMinChars(_taskName.value, 2)
        val isTaskDescriptionValid= hasMinChars(_taskDescription.value, 2)
        _hasErrors.value = isEmailValid && isTaskNameValid && isTaskDescriptionValid
    }

    val email : LiveData<String> get() = _email
    val taskName : LiveData<String> get() = _taskName
    val taskDescription : LiveData<String> get() = _taskDescription
    val formStatus : LiveData<Boolean> get() = _hasErrors

    val emailErrorMessage = Transformations.map(_email){ input ->
        if(isEmailValid(input)) return@map ""
        return@map "Email inválido, por favor inseria um e-mail válido"
    }

    val taskNameErrorMessage = Transformations.map(_taskName){ input ->
        if(hasMinChars(input, 2)) return@map ""
        return@map "Inclua uma tarefa válida"
    }

    val taskDescriptionErrorMessage = Transformations.map(_taskDescription){ input ->
        if(hasMinChars(input, 2)) return@map ""
        return@map "Inclua uma descrição válida"
    }

    @Suppress("UNUSED_PARAMETER")
    fun onEmailChanged(data: CharSequence, start: Int, before: Int, count: Int){
        _email.value = data.toString()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onTaskNameChanged(data: CharSequence, start: Int, before: Int, count: Int){
        _taskName.value = data.toString()
    }

    @Suppress("UNUSED_PARAMETER")
    fun onTaskDescriptionChanged(data: CharSequence, start: Int, before: Int, count: Int){
        _taskDescription.value = data.toString()
    }

}