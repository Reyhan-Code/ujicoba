package com.dicoding.ujicoba.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dicoding.ujicoba.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val lampViewModel: NotificationsViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Observe the lamp state from ViewModel
        lampViewModel.isLampOn.observe(viewLifecycleOwner, Observer { isOn ->
            binding.tvLampStatus.text = if (isOn) "Lamp is ON" else "Lamp is OFF"
        })

        // Set click listener to toggle lamp state
        binding.btnToggleLamp.setOnClickListener {
            lampViewModel.toggleLamp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}