package com.example.books.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.books.databinding.FilterOrderDialogBinding
import com.example.books.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterOrderBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FilterOrderDialogBinding
    private val searchViewModel: SearchViewModel by viewModels({requireParentFragment()})

    companion object {
        val TAG: String = FilterOrderBottomSheetFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterOrderDialogBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@FilterOrderBottomSheetFragment
            viewModel = searchViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}