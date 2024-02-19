package com.prueba.pruebaapp.ui.detail

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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        initUI()
        detailViewModel.getDetail(args.id)
        return binding.root
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                detailViewModel.state.collect{
                    when(it){
                        DetailState.Loading -> loadingState()
                        is DetailState.Error -> errorState()
                        is DetailState.Success -> successState(it)
                    }
                }
            }
        }
    }
    private fun successState(movie: DetailState.Success) {
        binding.pb.isVisible = false
        binding.image
        Glide.with(this).load(movie.movie.posterPath).into(binding.image)
        binding.clDetail.isVisible = true
        binding.tvNombre.text = movie.movie.title
        binding.tvNota.text = movie.movie.voteAverage.toString()
        binding.tvFecha.text = movie.movie.releaseDate
        binding.tvResumen.text= movie.movie.overview
    }

    private fun errorState() {
        binding.pb.isVisible = false
        //binding.clDetail.isVisible = true
    }

    private fun loadingState() {
        binding.pb.isVisible = true
        binding.clDetail.isVisible = false
    }

}