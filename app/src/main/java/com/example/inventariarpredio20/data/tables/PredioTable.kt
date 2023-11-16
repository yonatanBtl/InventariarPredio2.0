package com.example.inventariarpredio20.data.tables

import org.jetbrains.exposed.sql.Table


/* */
object PredioTable : Table("predio") {
    val idPredio = integer("id_predio").autoIncrement()
    val idTipoPredio = integer("id_tipo_predio").autoIncrement()
    val descripcion = varchar("descripcion", length = 255)
    val ruc = varchar("ruc", length = 20)
    val telefono = varchar("telefono", length = 10)
    val correo = varchar("correo", length = 80)
    val direccion = varchar("direccion", length = 100)
    val idUbigeo = varchar("idubigeo", length = 6)

    override val primaryKey = PrimaryKey(idPredio)
}

