package com.r2devpros.dependencyinjectiontraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class SecondActivity : AppCompatActivity() {

    //Inject by Lazy
    private val injectedCourse by inject<Course>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //region Classic approach
        val course = Course(
            Instructor(
                name = "Instructor",
                age = "28 y/o",
                license = "1234"
            )
        )
        course.doSomething("My Classic Approach")
        //endregion

        injectedCourse.doSomething("From Injected Object")
    }
}
