package com.prueba.pruebaapp.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import com.prueba.pruebaapp.R
import com.prueba.pruebaapp.databinding.FragmentMoviesBinding
import com.prueba.pruebaapp.domain.model.DataModel
import com.prueba.pruebaapp.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)

        initUI()

        return binding.root
    }

    private fun initUI() {
        /*    binding.btnCLick.setOnClickListener {
       findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment())
    }*/

        val list = ArrayList<DataModel>()
        list.add(DataModel(R.drawable.hacker, "Thi is cool 1"))
        list.add(DataModel(R.drawable.hacker, "Thi is cool 2"))
        list.add(DataModel(R.drawable.hacker, "Thi is cool 3"))
        list.add(DataModel(R.drawable.hacker, "Thi is cool 4"))
        // list.add(DataModel(R.drawable.londonlove, "Thi is cool"))

        val adapter = DataAdapter(list){item ->
            requireContext().showToast(item.text)
        }

        binding.recycler.apply {
            this.adapter = adapter
            set3DItem(true)
            setAlpha(true)

        }
    }

}