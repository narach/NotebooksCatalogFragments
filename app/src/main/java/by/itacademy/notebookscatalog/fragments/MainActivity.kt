package by.itacademy.notebookscatalog.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.itacademy.notebookscatalog.fragments.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
    }
}