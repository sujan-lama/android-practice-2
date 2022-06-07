package com.example.a20220606_sujanlama_nycschools.ui.schoolDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.a20220606_sujanlama_nycschools.databinding.FragmentSchoolDetailBinding
import com.example.a20220606_sujanlama_nycschools.model.School
import com.example.a20220606_sujanlama_nycschools.ui.schoolDetail.viewmodel.SchoolDetailViewModel
import com.example.a20220606_sujanlama_nycschools.utils.STATUS
import com.example.a20220606_sujanlama_nycschools.utils.hide
import com.example.a20220606_sujanlama_nycschools.utils.show
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [SchoolDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SchoolDetailFragment : Fragment() {

    private lateinit var binding: FragmentSchoolDetailBinding
    private val args: SchoolDetailFragmentArgs by navArgs()
    private lateinit var school: School

    private val viewModel: SchoolDetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        school = args.school
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.title = school.schoolName
            it.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        binding = FragmentSchoolDetailBinding.inflate(inflater, container, false)
            .also {
                it.school = school
            }

        viewModel.schoolSAT.observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS.SUCCESS -> {
                    it.data?.let { detail ->
                        binding.schoolSAT = detail
                    }
                    binding.group.show()
                    binding.progress.hide()
                }
                STATUS.ERROR -> {
                    binding.group.hide()
                    binding.progress.hide()
                    val error = it.message ?: "No SAT scores found"
                    Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
                }
                STATUS.LOADING -> {
                    binding.group.hide()
                    binding.progress.show()
                }
            }
        }

        viewModel.getSchoolSAT(school.id)

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            else -> super.onOptionsItemSelected(item)
        }
    }


}