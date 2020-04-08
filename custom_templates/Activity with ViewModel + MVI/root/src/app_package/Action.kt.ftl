package ${packageName}

sealed class ${prefixName}Action {
    data class SomeAction(val value_1: String? = "") ${prefixName}Action()
    object SomeAction2 : ${prefixName}Action()
}
