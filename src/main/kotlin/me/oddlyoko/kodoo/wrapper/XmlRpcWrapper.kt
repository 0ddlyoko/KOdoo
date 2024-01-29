package me.oddlyoko.kodoo.wrapper

import me.oddlyoko.kodoo.KOdoo
import me.oddlyoko.kodoo.models.Version

abstract class XmlRpcWrapper(
    val kOdoo: KOdoo,
) {
    abstract fun init()
    abstract fun getVersion(): Version
}
