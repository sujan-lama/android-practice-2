package com.example.a20220606_sujanlama_nycschools.ui.viewmodel.schoolList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20220606_sujanlama_nycschools.MainCoroutineRule
import com.example.a20220606_sujanlama_nycschools.getOrAwaitValue
import com.example.a20220606_sujanlama_nycschools.repository.FakeRepository
import com.example.a20220606_sujanlama_nycschools.ui.schoolList.viewmodel.SchoolViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

//@RunWith(MockitoJUnitRunner::class)
@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SchoolViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    lateinit var viewModel: SchoolViewModel


    private val repository: FakeRepository = FakeRepository()

    @Before
    fun setup() {
        viewModel = SchoolViewModel(repository)

    }


    @Test
    fun getSchoolList_returnTwoSchool() = runBlocking {

        // function call
        val response = viewModel.schoolsList.getOrAwaitValue()

        // verification
        assertThat(response.data!!.size, equalTo(2))
    }


}