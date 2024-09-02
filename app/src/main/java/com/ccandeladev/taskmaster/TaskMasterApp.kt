package com.ccandeladev.taskmaster

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Add to manifest android:name=".TaskMasterApp"

@HiltAndroidApp
class TaskMasterApp:Application() {
// Esta clase es el punto de entrada de Hilt para la inyección de dependencias


}