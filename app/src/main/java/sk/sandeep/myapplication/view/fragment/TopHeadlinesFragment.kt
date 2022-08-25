package sk.sandeep.myapplication.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import sk.sandeep.myapplication.adapter.NewsAdapter
import sk.sandeep.myapplication.databinding.FragmentTopHeadlinesBinding
import sk.sandeep.myapplication.util.Resource
import sk.sandeep.myapplication.view_model.NewsViewModel

@AndroidEntryPoint
class TopHeadlinesFragment : Fragment() {

    private var _binding: FragmentTopHeadlinesBinding? = null
    private val binding get() = _binding!!
    private val newsViewModel by viewModels<NewsViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        newsViewModel.getNews()
        initializeRecyclerView(binding.rvNoteList)
    }

    private fun initializeRecyclerView(rv: RecyclerView) {
        rv.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter()
        rv.adapter = newsAdapter
    }

    private fun bindObservers() {
        newsViewModel.newsLiveData.observe(viewLifecycleOwner) { newsData ->

            binding.progressBar.visibility = View.GONE
            when (newsData) {
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, newsData.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.differ.submitList(newsData.data!!.articles)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}