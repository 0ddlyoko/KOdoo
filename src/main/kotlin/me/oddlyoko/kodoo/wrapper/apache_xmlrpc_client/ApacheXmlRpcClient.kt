package me.oddlyoko.kodoo.wrapper.apache_xmlrpc_client

import me.oddlyoko.kodoo.models.ServerVersionInfo
import me.oddlyoko.kodoo.models.Version
import me.oddlyoko.kodoo.wrapper.XmlRpcWrapper
import org.apache.xmlrpc.XmlRpcException
import org.apache.xmlrpc.client.XmlRpcClient
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import java.net.URL
import kotlin.properties.Delegates

class ApacheXmlRpcClient: XmlRpcWrapper {
    private lateinit var baseUrl: String
    private lateinit var client: XmlRpcClient
    private lateinit var commonConfig: XmlRpcClientConfigImpl
    private lateinit var objectConfig: XmlRpcClientConfigImpl
    private lateinit var database: String
    private var uid by Delegates.notNull<Int>()
    private lateinit var password: String

    override fun login(baseUrl: String, database: String, user: String, password: String) {
        this.baseUrl = baseUrl
        this.client = XmlRpcClient()
        this.commonConfig = XmlRpcClientConfigImpl()
        this.commonConfig.serverURL = URL("${baseUrl}/xmlrpc/2/common")
        this.objectConfig = XmlRpcClientConfigImpl()
        this.objectConfig.serverURL = URL("${baseUrl}/xmlrpc/2/object")
        this.objectConfig.isEnabledForExtensions = true
        this.database = database
        val uid = client.execute(commonConfig, "authenticate", listOf(database, user, password, mapOf<Any, Any>()))
        if (uid is Boolean)
            throw IllegalStateException("Got $uid instead of a valid uid!")
        this.uid = uid as Int
        this.password = password
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

    override fun execute(
        methodName: String,
        targetModel: String,
        targetMethod: String,
        params: List<Any>,
        map: Map<String, Any?>,
    ): Any {
        try {
            return client.execute(objectConfig, methodName, listOf(database, uid, password, targetModel, targetMethod, params, map))
        } catch (ex: XmlRpcException) {
            throw me.oddlyoko.kodoo.exceptions.XmlRpcException("An exception has occurred while executing the RPC request:", ex)
        }
    }
}
