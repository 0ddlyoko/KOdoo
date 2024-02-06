package me.oddlyoko.kodoo.exceptions

import java.lang.Exception

class XmlRpcException(
    override val message: String?,
    override val cause: Throwable?,
): Exception(message, cause)
