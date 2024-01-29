package me.oddlyoko.kodoo

fun main() {
    val kOdoo = KOdoo(
        baseUrl = "http://localhost:8069",
//        baseUrl = "https://0ddlyoko.odoo.com",
        database = "odoo_skyblock",
        user = "admin",
        password = "admin",
    )
    val version = kOdoo.getVersion()
    println(version)
}
