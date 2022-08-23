package com.dirzaaulia.multimodulesample

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import com.dirzaaulia.multimodulesample.adapter.CommonLoadStateAdapter
import com.dirzaaulia.multimodulesample.adapter.HomeAdapter
import com.dirzaaulia.multimodulesample.databinding.ActivityMainBinding
import com.dirzaaulia.multimodulesample.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), HomeAdapter.HomeAdapterListener {

  private lateinit var binding: ActivityMainBinding

  private val viewModel: MainViewModel by viewModels()
  private val homeAdapter = HomeAdapter(this)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setup()
  }

  override fun onItemClicked(item: Movie) {

  }

  private fun setup() {
    initAdapter()
    subscribeDiscoverMovie()
  }

  private fun initAdapter() {
    binding.recyclerView.adapter = homeAdapter.withLoadStateFooter(
      CommonLoadStateAdapter { homeAdapter.retry() }
    )

    homeAdapter.addLoadStateListener { loadState ->
      binding.recyclerView.isVisible =
        loadState.source.refresh is LoadState.NotLoading
      binding.viewStatus.status.isVisible =
        loadState.source.refresh is LoadState.Error
      binding.viewStatus.buttonRetry.isVisible =
        loadState.source.refresh is LoadState.Error
      binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

      when (loadState.source.refresh) {
        is LoadState.NotLoading -> {
          if (homeAdapter.itemCount == 0) {
            binding.viewStatus.status.text = getString(R.string.empty_discover_movie)
          }
        }
        is LoadState.Error -> {
          if (isOnline(this)) {
            binding.viewStatus.status.text =
              (loadState.source.refresh as LoadState.Error).error.localizedMessage
          } else {
            binding.viewStatus.status.text = getString(R.string.no_internet)
          }
        }
        else -> {}
      }
    }
  }

  private fun subscribeDiscoverMovie() {
    lifecycleScope.launch {
      repeatOnLifecycle(Lifecycle.State.CREATED) {
        viewModel.movieList.collectLatest {
          homeAdapter.submitData(it)
        }
      }
    }
  }

  private fun isOnline(context: Context): Boolean {
    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
      connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
      when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
          Log.i("MainActivity", "NetworkCapabilities.TRANSPORT_CELLULAR")
          return true
        }
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
          Log.i("MainActivity", "NetworkCapabilities.TRANSPORT_WIFI")
          return true
        }
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
          Log.i("MainActivity", "NetworkCapabilities.TRANSPORT_ETHERNET")
          return true
        }
      }
    }
    return false
  }
}