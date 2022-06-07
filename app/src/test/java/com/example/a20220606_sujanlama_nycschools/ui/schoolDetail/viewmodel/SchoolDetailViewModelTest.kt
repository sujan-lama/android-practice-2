package com.example.a20220606_sujanlama_nycschools.ui.schoolDetail.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.a20220606_sujanlama_nycschools.MainCoroutineRule
import com.example.a20220606_sujanlama_nycschools.getOrAwaitValue
import com.example.a20220606_sujanlama_nycschools.repository.FakeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SchoolDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    lateinit var viewModel: SchoolDetailViewModel


    private val repository: FakeRepository = FakeRepository()

    @Before
    fun setup() {
        viewModel = SchoolDetailViewModel(repository)
    }


    @Test
    fun getSchoolSATofId123_returnSAT() = runBlocking {

        // function call
        viewModel.getSchoolSAT("123")
        val response = viewModel.schoolSAT.getOrAwaitValue()

        // verification
        assertThat(response.data?.id, `is`("123"))
    }

    @Test
    fun getSchoolSATofId1234_returnNull() = runBlocking {

        // function call
        viewModel.getSchoolSAT("1234")
        val response = viewModel.schoolSAT.getOrAwaitValue()

        // verification
        assertThat(response.data, nullValue())
    }


}