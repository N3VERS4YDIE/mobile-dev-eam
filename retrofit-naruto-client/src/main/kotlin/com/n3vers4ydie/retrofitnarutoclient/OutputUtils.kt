val TAB = "    "

fun printCharacter(character: Character?) {
    if (character == null) {
        return
    }

    printField("Id", character.id)
    printField("Name", character.name)
    printListField("Images", character.images)
    printFamily(character.family)
    printListField("Jutsu", character.jutsu)
    printListField("Tools", character.tools)
}

fun printFamily(family: Family?) {
    if (family == null) {
        return
    }

    val family_members =
            mapOf(
                    "Father" to family.father,
                    "Mother" to family.mother,
                    "Son" to family.son,
                    "Daughter" to family.daughter,
                    "Wife" to family.wife,
                    "Adoptive Son" to family.adoptiveSon,
                    "Godfather" to family.godfather
            )

    if (family_members.values.all { it != null }) {
        println("Family:")
        family_members.forEach { (name, member) -> printFamilyMember(name, member) }
    }
}

fun printFamilyMember(name: String, member: String?) {
    if (member != null) {
        println("$TAB$name: $member")
    }
}

fun <T> printField(name: String, value: T) {
    if (value != null) {
        println("$name: $value")
    }
}

fun <T> printListField(name: String, list: List<T>?) {
    val LIMIT = 10

    if (list != null && list.isNotEmpty()) {
        println("$name:")
        list.take(LIMIT).forEach { println("$TAB$it") }
        if (list.size > LIMIT) {
            println("$TAB... (Showing $LIMIT $name of ${list.size})")
        }
    }
}
