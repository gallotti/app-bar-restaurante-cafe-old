package com.gallotti.appcafebarrestaurante.feature.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.gallotti.appcafebarrestaurante.R
import com.gallotti.appcafebarrestaurante.extensions.hideLoadingDialog
import com.gallotti.appcafebarrestaurante.extensions.observeNonNull
import com.gallotti.appcafebarrestaurante.extensions.showLoadingDialog
import com.gallotti.appcafebarrestaurante.feature.detail.DetailStoreFragment
import com.gallotti.appcafebarrestaurante.feature.store.adapter.StoreListAdapter
import com.gallotti.appcafebarrestaurante.network.model.Store
import kotlinx.android.synthetic.main.fragment_list_store.*
import org.koin.android.architecture.ext.sharedViewModel

class StoreListFragment : androidx.fragment.app.Fragment() {

    private val viewModel: StoreListViewModel by sharedViewModel()

    private lateinit var storeListAdapter: StoreListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpStates()
        setUpSideEffect()
        setupStoreRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getListStore()
    }

    private fun setUpStates() {
        viewModel.state.observeNonNull(viewLifecycleOwner) { state ->

            if (state == StoreListViewModel.ScreenState.Loading) {
                activity?.showLoadingDialog()
            } else {
                activity?.hideLoadingDialog()
            }

            when (state) {
                is StoreListViewModel.ScreenState.OnGetListStore -> {
                    storeListAdapter.items = state.listStore
                }
                is StoreListViewModel.ScreenState.OnGetListStoreError -> {
                    //Exibir mensagem de erro
                }
            }
        }
    }

    private fun setUpSideEffect() {
        viewModel.events.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { sideEffect ->
            when (sideEffect) {
                    is StoreListViewModel.SideEffect.NavigateToDetailStore -> {
                        navigateToDetailStore(sideEffect.store)
                    }
                }
            }
        })
    }

    private fun setupStoreRecyclerView() {
        storeListAdapter = StoreListAdapter(StoreListAdapter.OnClickListener {
            viewModel.navigateToOptions(it)
        })
        with(recyclerViewStore) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
            adapter = storeListAdapter
        }
    }

    private fun navigateToDetailStore(store: Store) {
        if (requireActivity().supportFragmentManager.findFragmentByTag(DetailStoreFragment.TAG) == null) {
            val fragment = DetailStoreFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(DetailStoreFragment.DETAIL_STORE, store)
                }
            }

            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.frameLayout,
                fragment,
                DetailStoreFragment.TAG)
        }
    }

    companion object {
        val TAG = "store_fragment"
    }

}