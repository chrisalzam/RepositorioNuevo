package com.r2devpros.dependencyinjectiontraining.di

import com.r2devpros.dependencyinjectiontraining.Course
import com.r2devpros.dependencyinjectiontraining.Instructor
import org.koin.dsl.module

val courseModule = module {
    single {
        Instructor(
            name = "Juan Perez",
            age = "30 years old",
            license = "1234"
        )
    }

    single {
        Course(get())
    }
}
