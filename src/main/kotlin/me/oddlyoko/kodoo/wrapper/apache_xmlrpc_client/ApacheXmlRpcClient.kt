package me.oddlyoko.kodoo.wrapper.apache_xmlrpc_client

import me.oddlyoko.kodoo.KOdoo
import me.oddlyoko.kodoo.models.ServerVersionInfo
import me.oddlyoko.kodoo.models.Version
import me.oddlyoko.kodoo.wrapper.XmlRpcWrapper
import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import java.net.URL

class ApacheXmlRpcClient(
    kOdoo: KOdoo,
) : XmlRpcWrapper(
    kOdoo
) {
    private lateinit var client: XmlRpcClient
    private lateinit var commonConfig: XmlRpcClientConfigImpl

    override fun init() {
        client = XmlRpcClient()
        commonConfig = XmlRpcClientConfigImpl()
        commonConfig.serverURL = URL("${kOdoo.baseUrl}/xmlrpc/2/common")
    }

    override fun getVersion(): Version {
        val result = client.execute(commonConfig, "version", listOf<Any>()) as Map<String, Any>
        val serverVersionInfoMap = result["server_version_info"] as Array<Any>
        return Version(
            serverVersion = result["server_version"] as String,
            serverVersionInfo = ServerVersionInfo(
                major = serverVersionInfoMap[0].toString(),
                minor = serverVersionInfoMap[1] as Int,
                micro = serverVersionInfoMap[2] as Int,
                releaseLevel = serverVersionInfoMap[3] as String,
                serial = serverVersionInfoMap[4] as Int,
            ),
            serverSerie = result["server_serie"] as String,
            protocolVersion = result["protocol_version"] as Int,
        )
    }
}
