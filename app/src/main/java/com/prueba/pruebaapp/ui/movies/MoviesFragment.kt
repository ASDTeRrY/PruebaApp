package com.prueba.pruebaapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.FragmentMoviesBinding
import com.prueba.pruebaapp.databinding.ItemCountBinding
import com.prueba.pruebaapp.databinding.ItemViewBinding
import com.prueba.pruebaapp.domain.model.MovieModel
import com.prueba.pruebaapp.domain.model.Numbers
import com.prueba.pruebaapp.util.setHorizontalLayout
import com.prueba.pruebaapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by viewModels<MoviesViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        initUI()
        moviesViewModel.getMovie("1", "f46b58478f489737ad5a4651a4b25079")
        return binding.root
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        val countList = listOf(
            Numbers("1"), Numbers("2"), Numbers("3"), Numbers("4"), Numbers("5"),
            Numbers("6"), Numbers("7"), Numbers("8"), Numbers("9"), Numbers("10"),
            Numbers("11"), Numbers("12"), Numbers("13"), Numbers("14"), Numbers("15"),
        )
        binding.rvList.setUp(countList, R.layout.item_count, { number ->
            val view = ItemCountBinding.bind(this)
            view.tvName.text = number.id
            view.tvName.setOnClickListener {
                moviesViewModel.getMovie(number.id, "f46b58478f489737ad5a4651a4b25079")
            }
        }, {}, LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false))

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                moviesViewModel.state.collect {
                    when (it) {
                        MoviesState.Loading -> loadingState()
                        is MoviesState.Error -> errorState()
                        is MoviesState.Success -> successState(it.movie)
                    }
                }
            }
        }
    }

    private fun successState(movie: MovieModel) {
        binding.pb.isVisible = false
        binding.recycler.isVisible = true

        binding.recycler.setUp(movie.detailResponse, R.layout.item_view, { movie ->
            val view = ItemViewBinding.bind(this)
            Glide.with(view.image).load(movie.posterPath).placeholder(R.drawable.hacker).into(view.image)
            view.image.setOnClickListener {
                moviesViewModel.getMovie(movie.id.toString(), "f46b58478f489737ad5a4651a4b25079")
            }
        }, {})

        binding.recycler.apply {
            this.adapter = adapter
            set3DItem(true)
            setAlpha(true)
        }
    }

    private fun errorState() {
        binding.pb.isVisible = false
    }

    private fun loadingState() {
        binding.recycler.isVisible = false
        binding.pb.isVisible = true
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}