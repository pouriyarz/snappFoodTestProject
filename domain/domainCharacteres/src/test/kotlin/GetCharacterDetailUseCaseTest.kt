import com.rezaie.domain.domainCharacteres.base.onError
import com.rezaie.domain.domainCharacteres.base.onSuccess
import com.rezaie.domain.domainCharacteres.entity.CharacterDetailEntity
import com.rezaie.domain.domainCharacteres.entity.CharacterEntity
import com.rezaie.domain.domainCharacteres.entity.FilmsEntity
import com.rezaie.domain.domainCharacteres.entity.PlanetEntity
import com.rezaie.domain.domainCharacteres.entity.SpeciesEntity
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
import com.rezaie.domain.domainCharacteres.usecase.GetCharacterDetailUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.fail
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCharacterDetailUseCaseTest {

    private val characterRepository: CharacterRepository = mockk()
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase

    @Before
    fun setUp() {
        getCharacterDetailUseCase = GetCharacterDetailUseCase(characterRepository, testDispatcher)
    }

    @Test
    fun `execute should emit success and return character detail from repository`() =
        runTest(testDispatcher) {
            val characterEntity = CharacterEntity(
                id = 1,
                name = "Character 1",
                birthYear = "1990",
                height = "180",
                species = listOf("Human"),
                homeWorld = "Earth",
                films = listOf("Film 1", "Film 2"),
                url = "http://example.com"
            )

            val speciesEntity = SpeciesEntity(id = 1, language = "English", name = "Human")
            val planetEntity = PlanetEntity(id = 1, population = "7000")
            val filmsEntity =
                FilmsEntity(id = 1, name = "Film 1", description = "An epic adventure")

            val mockCharacterDetailEntity = CharacterDetailEntity(
                id = 1,
                name = "Character 1",
                birthYear = "1990",
                height = "180",
                species = listOf(speciesEntity),
                homeWorld = planetEntity,
                films = listOf(filmsEntity),
                url = "http://example.com"
            )

            coEvery {
                characterRepository.getCharacterDetail(characterEntity)
            } returns flowOf(mockCharacterDetailEntity)

            val result = getCharacterDetailUseCase(characterEntity)

            result.collect { resource ->
                resource.onSuccess { data ->
                    assertNotNull(data)
                    assertEquals("Character 1", data?.getName())
                    assertEquals("1990", data?.getBirthYear())
                    assertEquals("180", data?.getHeight())
                    assertEquals("Human", data?.getSpecies()?.firstOrNull()?.getName())
                    assertEquals("7000", data?.getHomeWorld()?.getPopulation())
                    assertEquals("Film 1", data?.getFilms()?.firstOrNull()?.getName())
                    assertEquals("http://example.com", data?.getUrl())
                }.onError { error ->
                    fail("Error should not be emitted, but got: ${error.message}")
                }
            }

            coVerify { characterRepository.getCharacterDetail(characterEntity) }
        }
}