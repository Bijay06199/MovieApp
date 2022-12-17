package com.example.movielover.ui.dashboard.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.movielover.BR
import com.example.movielover.R
import com.example.movielover.base.BaseFragment
import com.example.movielover.databinding.FragmentHomeBinding
import com.example.movielover.ui.PopularAdapter
import com.example.movielover.ui.dashboard.home.adapter.NowPlayingAdapter
import com.example.movielover.ui.dashboard.home.adapter.TrendingAdapter
import com.example.movielover.ui.dashboard.home.model.PopularResponse
import com.example.movielover.ui.dashboard.home.model.Result
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(),PopularAdapter.OnItemClicklistener,
    TrendingAdapter.OnItemClicklistener, NowPlayingAdapter.OnItemClicklistener {

    override fun getLayoutId(): Int = R.layout.fragment_home
    override fun getViewModel(): HomeViewModel {
        val viewModel: HomeViewModel by viewModel()
        return viewModel
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }


    lateinit var popularAdapter: PopularAdapter
    lateinit var trendingAdapter: TrendingAdapter
    lateinit var nowPlayingAdapter: NowPlayingAdapter
    var popularArrayList = ArrayList<Result>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }


    private fun initViews() {
        with(viewDataBinding) {
            with(mViewModel!!) {

                search.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                    Navigation.findNavController(requireView()).navigate(action)
                }

                getPopularMovies()
                getTrendingMovies()
                getNowPlayingMovies()

                popularResponse.observe(viewLifecycleOwner, Observer {
                    Log.d("responsee","https://image.tmdb.org/t/p/w500"+it.results[0].poster_path)

                    popularAdapter = PopularAdapter(it.results,requireContext(),this@HomeFragment)
                    popularRv.adapter = popularAdapter
                    popularAdapter.notifyDataSetChanged()

                })

                trendingResponse.observe(viewLifecycleOwner, Observer {
                    trendingAdapter = TrendingAdapter(it.results,requireContext(),this@HomeFragment)
                    trendingRv.adapter = trendingAdapter
                    trendingAdapter.notifyDataSetChanged()
                })

                nowPlayingResponse.observe(viewLifecycleOwner, Observer {
                    nowPlayingAdapter = NowPlayingAdapter(it.results,requireContext(),this@HomeFragment)
                    nowPlayingRv.adapter = nowPlayingAdapter
                    nowPlayingAdapter.notifyDataSetChanged()
                })
            }
        }
    }



    override fun itemClicked(position: Int, title: String, imageString: String, overview: String,id:Int) {

        val action = HomeFragmentDirections.actionHomeFragmentToDetailMovieFragment(
            title,imageString,overview,id
        )
        Navigation.findNavController(requireView()).navigate(action)


    }


}