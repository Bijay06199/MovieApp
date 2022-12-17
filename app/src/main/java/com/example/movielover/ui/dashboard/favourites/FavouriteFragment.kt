package com.example.movielover.ui.dashboard.favourites

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.movielover.BR
import com.example.movielover.R
import com.example.movielover.base.BaseFragment
import com.example.movielover.databinding.FragmentFavouriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavouriteFragment : BaseFragment<FragmentFavouriteBinding, FavouriteViewModel>(),FavouriteAdapter.OnItemClicklistener {
    override fun getLayoutId(): Int = R.layout.fragment_favourite
    override fun getViewModel(): FavouriteViewModel {
        val viewModel: FavouriteViewModel by viewModel()
        return viewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    lateinit var favouriteAdapter: FavouriteAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewDataBinding){
            with(mViewModel!!){
                getFavouriteMovies()

                favouriteMovieList.observe(viewLifecycleOwner, Observer {
                    favouriteAdapter = FavouriteAdapter(it,requireContext(),this@FavouriteFragment)
                    favouriteRv.adapter = favouriteAdapter
                })
            }
        }
    }

}