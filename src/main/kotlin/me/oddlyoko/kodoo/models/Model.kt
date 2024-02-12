package me.oddlyoko.kodoo.models

import me.oddlyoko.kodoo.KOdoo

abstract class Model(
    val kOdoo: KOdoo,
    val modelName: String,
) {

    open fun browse(ids: Array<Int>, fields: Array<String> = arrayOf()): List<Map<String, Any>> {
        return searchRead(arrayOf(arrayOf("id", "in", ids)), fields = fields)
    }

    open fun search(domain: Array<Array<Any>>, offset: Int = 0, limit: Int = 0, order: String? = null): List<Int> {
        val result = kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "search",
            params = listOf(domain),
            map = mapOf("offset" to offset, "limit" to limit, "order" to order),
        )
        if (result is Array<*>)
            return result.map { it as Int }
        return arrayListOf()
    }

    open fun searchCount(domain: Array<Array<Any>>, limit: Int = 0): Int {
        val result = kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "search_count",
            params = listOf(domain),
            map = mapOf("limit" to limit),
        )
        return result as Int
    }

    open fun searchRead(domain: Array<Array<Any>>, fields: Array<String> = arrayOf(), offset: Int = 0, limit: Int = 0, order: String? = null, extraArgs: Map<String, Any> = mapOf()): List<Map<String, Any>> {
        val map = extraArgs.toMutableMap()
        map["offset"] = offset
        map["limit"] = limit
        order?.let { map["order"] = it }
        val result = kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "search_read",
            params = listOf(domain, fields),
            map = map,
        )
        if (result is Array<*>) {
            return result.map { it as Map<String, Any> }
        }
        return listOf()
    }

    open fun create(vals: Array<Map<String, Any>>): List<Int> {
        val result = kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "create",
            params = listOf(vals),
        )
        if (result is Array<*>)
            return result.map { it as Int }
        return listOf()
    }

    open fun write(ids: Array<Int>, vals: Map<String, Any>): Boolean {
        val result = kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "write",
            params = listOf(ids, vals),
        )
        return result as Boolean
    }

    open fun unlink(ids: Array<Int>) {
        kOdoo.executeKw(
            targetModel = modelName,
            targetMethod = "unlink",
            params = listOf(ids),
        )
    }
}
