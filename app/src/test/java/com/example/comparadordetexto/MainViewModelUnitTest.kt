package com.example.comparadordetexto

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.comparadordetexto.view.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModelUnitTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = MainViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun mainViewModel_CheckInitialValue(): Unit = runTest {
        val value = viewModel.resultado.value?.iguales
        assertEquals("", value)
    }

    @Test
    fun mainViewModel_TestEqualValue(): Unit = runTest {
        launch{
            viewModel.compareStrings("Azul","Azul")
        }
        advanceUntilIdle()
        val value = viewModel.resultado.value?.iguales
        assertEquals("Las cadenas ingresadas son iguales", value)
    }

    @Test
    fun mainViewModel_TestDistintValue(): Unit = runTest {
        launch{
            viewModel.compareStrings("Azul","Rojo")
        }
        advanceUntilIdle()
        val value = viewModel.resultado.value?.iguales
        assertEquals("Las cadenas ingresadas no son iguales", value)
    }

}