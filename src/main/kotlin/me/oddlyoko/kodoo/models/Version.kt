package me.oddlyoko.kodoo.models

data class Version(
    val serverVersion: String,
    val serverVersionInfo: ServerVersionInfo,
    val serverSerie: String,
    val protocolVersion: Int,
)

data class ServerVersionInfo(
    val major: String,
    val minor: Int,
    val micro: Int,
    val releaseLevel: String,
    val serial: Int,
)
