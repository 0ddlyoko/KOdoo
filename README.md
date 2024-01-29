# KOdoo
A Kotlin library for building XML-RPC client to an Odoo instance

## How to use
First, make an instance of KOdoo
```kotlin
val kOdoo = KOdoo(
    baseUrl = "http://localhost:8069",
    database = "odoo_db",
    user = "admin",
    password = "admin",
)
```

Now, using this instance, you can retrieve version
```kotlin
val version = kOdoo.getVersion()
```

And do much more!