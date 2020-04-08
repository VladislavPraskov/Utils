package ${packageName}

sealed class ${prefixName}ResultAction {
    object Loading : ${prefixName}ResultAction()
    data class SomeAction(val p0: String?="") : ${prefixName}ResultAction()
}
