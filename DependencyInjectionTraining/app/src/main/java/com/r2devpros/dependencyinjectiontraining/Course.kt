package com.r2devpros.dependencyinjectiontraining

import android.util.Log

class Course(
    private val instructor: Instructor
) {

    fun doSomething(from: String) {
        Log.d("MyClass_TAG", "doSomething: from: $from")
    }
}