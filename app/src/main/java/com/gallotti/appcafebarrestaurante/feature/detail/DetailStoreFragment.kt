package com.gallotti.appcafebarrestaurante.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gallotti.appcafebarrestaurante.R
import com.gallotti.appcafebarrestaurante.extensions.hideLoadingDialog
import com.gallotti.appcafebarrestaurante.extensions.observeNonNull
import com.gallotti.appcafebarrestaurante.extensions.serializableArgument
import com.gallotti.appcafebarrestaurante.extensions.showLoadingDialog
import com.gallotti.appcafebarrestaurante.network.model.Store
import org.koin.android.architecture.ext.sharedViewModel

class DetailStoreFragment : androidx.fragment.app.Fragment() {

    private val viewModel: DetailStoreViewModel by sharedViewModel()
    private val store: Store by lazy { serializableArgument<Store>(DETAIL_STORE)!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_store, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDetailStore(store)
    }

    private fun bindViewModel() {
        viewModel.state.observeNonNull(viewLifecycleOwner) { state ->

            if (state == DetailStoreViewModel.ScreenState.Loading) {
                activity?.showLoadingDialog()
            } else {
                activity?.hideLoadingDialog()
            }

            when (state) {
                is DetailStoreViewModel.ScreenState.OnGetDetailStore -> {
                    //Popular a tela com as informações do detalhe da loja
                }
                is DetailStoreViewModel.ScreenState.OnGeDetailStoreError -> {
                    //Exibir mensagem de error
                }
            }
        }
    }

    companion object {
        const val TAG = "detail_store_fragment"
        const val DETAIL_STORE = "detail_store"
    }

}