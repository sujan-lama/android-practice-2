package com.example.a20220606_sujanlama_nycschools.ui.schoolList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a20220606_sujanlama_nycschools.databinding.FragmentSchoolListBinding
import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.ui.schoolList.adapter.OnSchoolClickListener
import com.example.a20220606_sujanlama_nycschools.ui.schoolList.adapter.SchoolListAdapter
import com.example.a20220606_sujanlama_nycschools.ui.schoolList.viewmodel.SchoolViewModel
import com.example.a20220606_sujanlama_nycschools.utils.STATUS
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [SchoolListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SchoolListFragment : Fragment(), OnSchoolClickListener {

    private lateinit var binding: FragmentSchoolListBinding
    private val viewModel: SchoolViewModel by viewModels()
    private val schoolListAdapter = SchoolListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.title = "NYC Schools"
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setHasOptionsMenu(false)

        // Inflate the layout for this fragment
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)

        with(binding.rvSchoolList) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = schoolListAdapter
        }

        binding.swiperefresh.setOnRefreshListener {
            viewModel.getSchools()
        }

        viewModel.schoolsList.observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS.SUCCESS -> {
                    schoolListAdapter.updateList(it.data ?: listOf())
                    binding.swiperefresh.isRefreshing = false
                }
                STATUS.ERROR -> {
                    binding.swiperefresh.isRefreshing = false
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                STATUS.LOADING -> {
                    binding.swiperefresh.isRefreshing = true
                }
            }
        }

        return binding.root
    }

    override fun onClick(school: School) {
        val action =
            SchoolListFragmentDirections.actionSchoolListFragmentToSchoolDetailFragment(school)
        findNavController().navigate(action)
    }

}