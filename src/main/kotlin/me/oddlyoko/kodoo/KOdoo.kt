package me.oddlyoko.kodoo

import me.oddlyoko.kodoo.models.Model
import me.oddlyoko.kodoo.models.Version
import me.oddlyoko.kodoo.wrapper.XmlRpcWrapper
import me.oddlyoko.kodoo.wrapper.apache_xmlrpc_client.ApacheXmlRpcClient

class KOdoo: XmlRpcWrapper {
    private val xmlRpcWrapper: XmlRpcWrapper = ApacheXmlRpcClient()
    private val models: HashMap<Class<out Model>, Model> = hashMapOf()

    fun register(model: Model) {
        models[model.javaClass] = model
    }

    operator fun <E: Model> get(model: Class<E>): E {
        return models[model] as E
    }

    override fun login(baseUrl: String, database: String, user: String, password: String) {
        xmlRpcWrapper.login(baseUrl, database, user, password)
    }

    // TODO Save version internally to avoid always sending request
    override fun getVersion(): Version = xmlRpcWrapper.getVersion()

    override fun execute(methodName: String, targetModel: String, targetMethod: String, params: List<Any>, map: Map<String, Any?>): Any =
        xmlRpcWrapper.execute(methodName = methodName, targetModel = targetModel, targetMethod = targetMethod, params = params, map = map)
}
