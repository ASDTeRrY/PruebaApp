package com.prueba.pruebaapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.FragmentDetailBinding
import com.prueba.pruebaapp.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)


    /*    binding.btnCLick.setOnClickListener {
           findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment())
        }*/
        binding.carouselRecyclerview.adapter = adapter
        binding.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }

        return binding.root
    }

}