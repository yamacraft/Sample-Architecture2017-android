package io.github.yamacraft.app.sample.architecture2017

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.yamacraft.app.sample.architecture2017.databinding.ActivityMainBinding
import io.github.yamacraft.app.sample.architecture2017.viewmodel.GithubViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val githubViewModel: GithubViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            binding = this
            setContentView(binding.root)
        }

        githubViewModel.repositoriesLiveData.observe(this) {
            binding.mainText.text = it.toString()
        }

        githubViewModel.getRepositories("kotlin")
    }
}
