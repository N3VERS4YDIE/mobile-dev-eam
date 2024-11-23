data class CharactersResponse(val characters: List<Character>)

data class Character(
        val id: Int,
        val name: String,
        val images: List<String>?,
        val family: Family?,
        val jutsu: List<String>?,
        val tools: List<String>?
)

data class Family(
        val father: String?,
        val mother: String?,
        val son: String?,
        val daughter: String?,
        val wife: String?,
        val adoptiveSon: String?,
        val godfather: String?
)
