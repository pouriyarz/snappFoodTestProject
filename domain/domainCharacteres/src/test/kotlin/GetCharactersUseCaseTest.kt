import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import com.rezaie.domain.domainCharacteres.usecase.GetCharactersUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    private val characterRepository: CharacterRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getCharactersUseCase: GetCharactersUseCase

    @Before
    fun setUp() {
        getCharactersUseCase = GetCharactersUseCase(characterRepository, testDispatcher)
    }

    @Test
    fun `execute should return paging data from repository`() = runTest(testDispatcher) {
        val query = "test query"
        val pagingConfig = PagingConfig(pageSize = 20)
        val params = GetCharactersUseCase.Params(query, pagingConfig)

        val mockCharacterEntity = CharacterEntity(
            id = 1,
            name = "Character 1",
            birthYear = "1990",
            height = "180",
            species = listOf("Human"),
            homeWorld = "Earth",
            films = listOf("Film 1", "Film 2"),
            url = "http://example.com"
        )

        val mockPagingData = PagingData.from(listOf(mockCharacterEntity))

        coEvery {
            characterRepository.getCharacters(query, pagingConfig)
        } returns flowOf(mockPagingData)

        val result = getCharactersUseCase.execute(params)
        val characterList = mutableListOf<CharacterEntity>()

        result.collect { pagingData ->
            pagingData.map {
                characterList.add(it)

                assertTrue("Character list should not be empty", characterList.isNotEmpty())

                val character = characterList.firstOrNull()

                assertNotNull("Character should not be null", character)
                assertEquals("Character ID should be 1", 1, character?.getId())
                assertEquals(
                    "Character name should be 'Character 1'",
                    "Character 1",
                    character?.getName()
                )
                assertEquals(
                    "Character birth year should be '1990'",
                    "1990",
                    character?.getBirthYear()
                )
                assertEquals("Character height should be '180'", "180", character?.getHeight())
                assertTrue(
                    "Character species should contain 'Human'",
                    character?.getSpecies()?.contains("Human") == true
                )
                assertEquals(
                    "Character homeWorld should be 'Earth'",
                    "Earth",
                    character?.getHomeWorld()
                )
                assertTrue(
                    "Character films should contain 'Film 1'",
                    character?.getFilms()?.contains("Film 1") == true
                )
                assertEquals(
                    "Character URL should be 'http://example.com'",
                    "http://example.com",
                    character?.getUrl()
                )
            }
        }
    }
}