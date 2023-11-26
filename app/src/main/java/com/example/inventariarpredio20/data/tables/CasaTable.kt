package com.example.inventariarpredio20.data.tables

import org.jetbrains.exposed.sql.Table

object CasaTable: Table("casa") {
    val idCasa = integer("id_casa").autoIncrement()
    val idPredio = integer("id_predio") references PredioTable.idPredio
    val idEstado = integer("id_estado")
    val idPredioMdu = integer("id_predio_mdu")
    val numero = short("numero")
    val piso = short("piso").nullable()
    val area =  decimal("area", precision = 15, scale = 2)
    val participacion = decimal("participacion", precision = 6, scale = 2).nullable()

    override val primaryKey = PrimaryKey(idCasa)
}

/*
object CasaTable: Table("casa") {
    val idCasa = integer("id_casa").autoIncrement()
    val idPredio = integer("id_predio")
    val idEstado = integer("id_estado")
    val idPredioMdu = integer("id_predio_mdu")
    // val idEstado = integer("id_estado")
    //val idPredioMdu = integer("id_predio_mdu")
    val numero = short("numero")
    val piso = short("piso").nullable()
    val area = decimal("area", precision = 15, scale = 2)
    val participacion = decimal("participacion", precision = 6, scale = 2).nullable()

    override val primaryKey = PrimaryKey(idCasa)
}


 */


/*

    val idCasa = integer("id_casa").autoIncrement()
    val idPredio = integer("id_predio")
    val idEstado = CasaEstadoTable.integer("id_estado")
    val idPredioMdu = PredioMDUTable.integer("id_predio_mdu")
*/

