package io.github.yamacraft.app.sample.architecture2017.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.github.yamacraft.app.sample.architecture2017.repository.GithubRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GithubViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var itemObserver: Observer<List<String>>

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun getOrganizationRepository_success() = runTest {
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        every { itemObserver.onChanged(any()) } answers {}

        // Repositoryをmockして決めた値を返すように設定し、実行
        val repositories = listOf(
            "list1",
            "list2",
            "list3"
        )
        val repository = mockk<GithubRepository> {
            coEvery {
                getOrganizationRepositories(any())
            } returns Result.success(repositories)
        }
        val viewModel = GithubViewModel(repository)
        viewModel.repositoriesLiveData.observeForever(itemObserver)
        try {
            viewModel.getRepositories("example")
        } finally {
            Dispatchers.resetMain()
        }

        // 意図したRepositoryのメソッドを呼べているかと中身のチェック
        coVerifyOrder {
            repository.getOrganizationRepositories("example")
        }
        val items = requireNotNull(viewModel.repositoriesLiveData.value)
        assertThat(items.size, `is`(3))
        assertThat(items[0], `is`("list1"))
        assertThat(items[1], `is`("list2"))
        assertThat(items[2], `is`("list3"))
    }
}
