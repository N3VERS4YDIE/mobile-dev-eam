import kotlin.io.println
import kotlin.system.exitProcess
import retrofit2.Response

fun main() {
    val CHARACTERS_LIMIT = 5
    val response: Response<CharactersResponse> =
            service.getAllCharacters(limit = CHARACTERS_LIMIT).execute()

    println("- GET /characters?limit=$CHARACTERS_LIMIT\n")

    if (response.isSuccessful) {
        val characters = response.body()?.characters

        characters?.forEach {
            printCharacter(it)
            println()
        }
    } else {
        println("Error: ${response.code()}")
    }

    val CHARACTER_ID = 1293 // Itachi Uchiha
    val characterResponse: Response<Character> =
            service.getCharacterById(id = CHARACTER_ID).execute()

    println("\n- GET /characters/$CHARACTER_ID\n")

    if (characterResponse.isSuccessful) {
        val character = characterResponse.body()
        printCharacter(character)
    } else {
        println("Error: ${characterResponse.code()}")
    }

    exitProcess(0)
}
