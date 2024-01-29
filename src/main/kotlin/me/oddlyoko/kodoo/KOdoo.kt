package me.oddlyoko.kodoo

import me.oddlyoko.kodoo.models.Version
import me.oddlyoko.kodoo.wrapper.XmlRpcWrapper
import me.oddlyoko.kodoo.wrapper.apache_xmlrpc_client.ApacheXmlRpcClient

class KOdoo(
    val baseUrl: String,
    val database: String,
    val user: String,
    val password: String,
) {
    private val xmlRpcWrapper: XmlRpcWrapper = ApacheXmlRpcClient(this)

    // TODO Save version internally to avoid always sending request
    fun getVersion(): Version = xmlRpcWrapper.getVersion()

    init {
        xmlRpcWrapper.init()
    }
}
