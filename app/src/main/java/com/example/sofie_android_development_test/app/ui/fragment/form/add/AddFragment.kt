package com.example.sofie_android_development_test.app.ui.fragment.form.add

import com.example.sofie_android_development_test.R
import com.example.sofie_android_development_test.app.ui.fragment.form.FormFragment

class AddFragment : FormFragment() {

    override val toolbarTitle: String by lazy { resources.getString(R.string.add_task_title) }

    override fun sendTaskSuccess(title: String, email: String, description: String) {

    }
}
