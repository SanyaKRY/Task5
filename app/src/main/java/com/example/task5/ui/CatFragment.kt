package com.example.task5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.task5.databinding.FragmentCatBinding

class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args: CatFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.apply {
            Glide.with(view.context).load(args.cat?.imageUrl).into(catItemImageView)
            catItemTextView.text = args.cat?.id
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}