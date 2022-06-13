package com.example.books.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.books.R
import com.example.books.adapter.SearchListAdapter
import com.example.books.databinding.SearchFragmentBinding
import com.example.books.model.Volume
import com.example.books.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var binding: SearchFragmentBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@SearchFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        val searchListAdapter = SearchListAdapter(
            object: DiffUtil.ItemCallback<Volume>() {
                override fun areItemsTheSame(oldItem: Volume, newItem: Volume): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Volume, newItem: Volume): Boolean {
                    return oldItem == newItem
                }
            }
        )

        binding.searchView.apply {
            isIconified = false
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        searchViewModel.onQueryChange(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        if (it.isNotEmpty() && it.length % 2 == 0) {
                            // submit the search
                            searchViewModel.onQueryChange(it)
                        }

                        if (it.isEmpty()) {
                            // clear the search
                            searchViewModel.onQueryChange("")
                        }
                    }
                    return true
                }
            })
        }

        binding.searchRecyclerView.apply {
            adapter = searchListAdapter
            addItemDecoration(object: RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.top = resources.getDimensionPixelSize(R.dimen.recycler_view_gaps)
                    outRect.left = resources.getDimensionPixelSize(R.dimen.recycler_view_gaps)
                    outRect.right = resources.getDimensionPixelSize(R.dimen.recycler_view_gaps)
                    outRect.bottom = resources.getDimensionPixelSize(R.dimen.recycler_view_gaps)
                }
            })
        }

        binding.filterCategoryChip.setOnClickListener {
            FilterCategoryBottomSheetFragment()
                .show(childFragmentManager, FilterCategoryBottomSheetFragment.TAG)
        }

        binding.filterOrderChip.setOnClickListener {
            FilterOrderBottomSheetFragment()
                .show(childFragmentManager, FilterOrderBottomSheetFragment.TAG)
        }

        lifecycleScope.launchWhenCreated {
            searchViewModel.volumePagingItems.collectLatest {
                searchListAdapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            searchViewModel.volumeFilter.collectLatest {
                binding.filter = it
            }
        }
    }
}