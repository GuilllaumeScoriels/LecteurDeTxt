package com.example.a18

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a18.TextFileImporter
import com.example.lecturemotparmotapp.LectureViewModel

class LectureViewModelFactory(
    private val importer: TextFileImporter
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LectureViewModel::class.java)) {
            return LectureViewModel(importer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
