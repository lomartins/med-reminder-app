import dev.luisamartins.medreminder.data.medication.MedicationDao
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.repository.medication.MedicationRepositoryImpl
import dev.luisamartins.medreminder.repository.medication.toEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MedicationRepositoryImplTest {

    private lateinit var medicationDao: MedicationDao
    private lateinit var medicationRepository: MedicationRepositoryImpl

    @Before
    fun setUp() {
        medicationDao = mockk()
        medicationRepository = MedicationRepositoryImpl(medicationDao)
    }

    private val medicationsStub = listOf(
        Medication(
            id = 1,
            name = "Medication",
            dosage = 1,
            time = "12:00",
            dosageUnit = DosageUnit.Pills,
            type = MedicationType.PILL,
            startDate = "2022-01-01"
        )
    )

    @Test
    fun getAll_returnsListOfMedications() = runBlocking {
        coEvery { medicationDao.getAll() } returns medicationsStub.map { it.toEntity() }

        val result = medicationRepository.getAll()

        assertEquals(medicationsStub, result)
        coVerify { medicationDao.getAll() }
    }

    @Test
    fun getById_returnsMedication() = runBlocking {
        coEvery { medicationDao.getById(1L) } returns medicationsStub.first().toEntity()

        val result = medicationRepository.getById(1L)

        assertEquals(medicationsStub.first(), result)
        coVerify { medicationDao.getById(1L) }
    }

    @Test
    fun insertAll_insertsMedications() = runBlocking {
        coEvery { medicationDao.insertAll(*anyVararg()) } returns Unit

        medicationRepository.insertAll(*medicationsStub.toTypedArray())

        coVerify { medicationDao.insertAll(*medicationsStub.map { it.toEntity() }.toTypedArray()) }
    }
}
