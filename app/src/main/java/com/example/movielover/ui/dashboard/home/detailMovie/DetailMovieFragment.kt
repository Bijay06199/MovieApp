package com.example.movielover.ui.dashboard.home.detailMovie

import android.media.MediaCodecInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.movielover.BR
import com.example.movielover.R
import com.example.movielover.base.BaseFragment
import com.example.movielover.data.room.model.FavouriteModel
import com.example.movielover.databinding.FragmentDetailMovieBinding
import com.example.movielover.ui.dashboard.home.detailMovie.adapter.BillCastAdapter
import com.example.movielover.ui.dashboard.home.detailMovie.model.ProfileModel
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding,DetailMovieViewModel>() {

    override fun getLayoutId(): Int =R.layout.fragment_detail_movie


    override fun getViewModel(): DetailMovieViewModel {
        val viewModel: DetailMovieViewModel by viewModel()
        return viewModel
    }
    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    var title:String=""
    var image:String=""
    var overviewString:String=""
    var movieId:Int =0
    lateinit var billCastAdapter: BillCastAdapter
    var profileList=ArrayList<ProfileModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = DetailMovieFragmentArgs.fromBundle(requireArguments()).title
        image = DetailMovieFragmentArgs.fromBundle(requireArguments()).imageString
        overviewString = DetailMovieFragmentArgs.fromBundle(requireArguments()).overview
        movieId = DetailMovieFragmentArgs.fromBundle(requireArguments()).id
        getFavouriteMovies()
        initViews()
        initRecyclerView()
    }

    private fun getFavouriteMovies() {
        with(viewDataBinding){
            with(mViewModel!!){
                getFavouriteMovies()
                favouriteMovieList.observe(viewLifecycleOwner, Observer {
                    it.forEach {
                        if (it.movieId==movieId){
                            favouriteIcon.setImageDrawable(resources.getDrawable(R.drawable.favourite_selected))
                            favouriteIcon.isEnabled=false
                        }

                    }
                })
            }
        }
    }

    private fun initRecyclerView() {
        with(viewDataBinding){
            billCastAdapter = BillCastAdapter(profileList,requireContext())
            billCastRv.adapter = billCastAdapter

        }
    }

    private fun initViews() {
        with(viewDataBinding){
            headerTitle.setText(title)
            Glide.with(requireContext())
                .asBitmap()
                .fitCenter()
                .load(image)
                .into(movieImage)

            overviewDesc.setText(overviewString)

            toolbar.setNavigationOnClickListener{
                requireActivity().onBackPressed()

            }
            profileList.add(ProfileModel("https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg","Isabelle Funhram"))
            profileList.add(ProfileModel("https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg","Isabelle Funhram"))
            profileList.add(ProfileModel("https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg","Isabelle Funhram"))
            profileList.add(ProfileModel("https://image.tmdb.org/t/p/w500/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg","Isabelle Funhram"))


            with(mViewModel!!){

                favouriteIcon.setOnClickListener {

                    favouriteIcon.setImageDrawable(resources.getDrawable(R.drawable.favourite_selected))
                    Toast.makeText(requireContext(),"Movie added to favourite",Toast.LENGTH_SHORT).show()
                    saveFavourite(FavouriteModel(movieImage = image, movieName = title,movieId))

                }
            }


        }


    }


}