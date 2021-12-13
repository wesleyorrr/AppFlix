package br.com.map.appflix.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.map.appflix.R
import br.com.map.appflix.domain.Movie
import br.com.map.appflix.framework.api.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieListViewModel = ViewModelProvider.NewInstanceFactory().create(MovieListViewModel::class.java)
        movieListViewModel.init()
        initObserver()
      loadingVisibility(true)
    }
    private fun initObserver(){
      movieListViewModel.moviesList.observe( this,{
          list->
          if (list.isNotEmpty()){
              populateList(list)
              loadingVisibility(false)
          }

          populateList(list)
      })
    }
    private fun populateList(list: List<Movie>) {
        moviesList.apply {

            hasFixedSize()
            adapter = MoviesAdapter(list)

        }
    }
    private fun loadingVisibility(isLoading:Boolean){
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}