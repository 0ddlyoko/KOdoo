package me.oddlyoko.kodoo

import me.oddlyoko.kodoo.models.models.ResPartner

fun main() {
    val kOdoo = KOdoo()
    println("Logging in ...")
    kOdoo.login(
        baseUrl = "http://localhost:8069",
        database = "odoo_skyblock",
        user = "admin",
        password = "admin",
    )
    println("Logged!")
    val version = kOdoo.getVersion()
    println("Version: $version")

//    val result = kOdoo.executeKw(
//        targetModel = "res.partner",
//        targetMethod = "check_access_rights",
//        params = listOf("read"),
//        map = mapOf("raise_exception" to false),
//    )
//    println("Result = $result")

    // Register all models
    kOdoo.register(ResPartner(kOdoo))

    val Partners = kOdoo[ResPartner::class.java]

    // Search on all partners
//    val partners = Partners.search(arrayOf(arrayOf("name", "ilike", "a")), limit = 5)
//    println("Partners = $partners")
//
//    val partnerCount = Partners.searchCount(arrayOf(arrayOf("name", "ilike", "a")))
//    println("Partners = $partnerCount")
//
//    val partnerRead = Partners.searchRead(arrayOf(arrayOf("name", "ilike", "a")), arrayOf("name"))
//    println("Partners = $partnerRead")

//    val newPartner = Partners.create(arrayOf(
//        mapOf(
//            "name" to "Test",
//        )
//    ))
//    println("New partner = $newPartner")
    val newPartner = Partners.search(arrayOf(arrayOf("name", "ilike", "Test")), limit=1)

    val partner = Partners.browse(newPartner.toTypedArray(), arrayOf("name", "create_date"))
    println("Partner = $partner")

    val partnerId = partner[0]["id"] as Int
    println("Partner id = $partnerId")

    val result = Partners.write(arrayOf(partnerId), mapOf(
        "phone" to "+32496012345",
    ))
    println("Result = $result")
}
