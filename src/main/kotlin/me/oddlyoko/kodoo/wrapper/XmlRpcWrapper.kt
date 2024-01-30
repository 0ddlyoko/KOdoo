package me.oddlyoko.kodoo.wrapper

import me.oddlyoko.kodoo.models.Version

interface XmlRpcWrapper {
    fun login(baseUrl: String, database: String, user: String, password: String)
    fun getVersion(): Version
    fun execute(methodName: String, targetModel: String, targetMethod: String, params: List<Any> = listOf(), map: Map<String, Any?> = mapOf()): Any

    fun executeKw(targetModel: String, targetMethod: String, params: List<Any> = listOf(), map: Map<String, Any?> = mapOf()): Any =
        execute("execute_kw", targetModel, targetMethod, params, map)
}
