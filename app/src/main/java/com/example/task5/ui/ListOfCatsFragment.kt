package com.example.task5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.task5.CatAdapter
import com.example.task5.CatClickListener
import com.example.task5.CatViewModel
import com.example.task5.databinding.FragmentListOfCatsBinding

class ListOfCatsFragment : Fragment() {

    private lateinit var itemAdapter: CatAdapter
    private val catViewModel by viewModels<CatViewModel>()
    private lateinit var recycler: RecyclerView

    private var _binding: FragmentListOfCatsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfCatsBinding.inflate(inflater, container, false)
        val view = binding.root

        itemAdapter = CatAdapter(CatClickListener { cat -> findNavController().navigate(ListOfCatsFragmentDirections
            .actionListOfCatsFragmentToCatFragment(cat)) })
        recycler = binding.recyclerView
        recycler.apply {
            adapter = itemAdapter
            layoutManager = LinearLayoutManager(context)
        }
        catViewModel.items.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer
            itemAdapter.addItems(it)
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}