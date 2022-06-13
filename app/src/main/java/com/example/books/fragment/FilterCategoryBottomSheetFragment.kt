package com.example.books.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.books.databinding.FilterCategoryDialogBinding
import com.example.books.viewmodel.SearchViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterCategoryBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FilterCategoryDialogBinding
    private val searchViewModel: SearchViewModel by viewModels({requireParentFragment()})

    companion object {
        val TAG: String = FilterCategoryBottomSheetFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FilterCategoryDialogBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@FilterCategoryBottomSheetFragment
            viewModel = searchViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}