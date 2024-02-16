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
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.FragmentMoviesBinding
import com.prueba.pruebaapp.domain.model.DataModel
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                moviesViewModel.state.collect{
                    when(it){
                        MoviesState.Loading -> loadingState()
                        is MoviesState.Error -> errorState()
                        is MoviesState.Success -> successState(it)
                    }
                }
            }
        }
    }

    private fun successState(movie: MoviesState.Success) {
        binding.pb.isVisible = false
        binding.recycler.isVisible = true
        /*    binding.btnCLick.setOnClickListener {
findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment())
}*/


        val adapter = DataAdapter(movie.movie.detailResponse){item ->
            //requireContext().showToast(item.text)
        }

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

}