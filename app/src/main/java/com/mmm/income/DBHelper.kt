package com.mmm.income

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(
    context: Context?
) : SQLiteOpenHelper(context, "Expense.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql =
            "CREATE TABLE trans(id INTEGER PRIMARY KEY AUTOINCREMENT, amt INTEGER, category TEXT, note TEXT, isexpense INTEGER)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addTrans(trans: TransModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amt", amt)
                put("category", category)
                put("note", note)
                put("isexpense", isExpense)
            }
        }
        db.insert("trans", null, values)

    }


    fun getTransaction(): ArrayList<TransModel> {
        var transList = ArrayList<TransModel>()
        var db = readableDatabase
        var sql = "SELECT * FROM trans"
        var cursor = db.rawQuery(sql, null)
        cursor.moveToFirst()

        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var amt = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpense = cursor.getInt(4)
            var data = TransModel(id, amt, category, note, isExpense)
            transList.add(data)
            cursor.moveToNext()
        }
        return transList
    }

    fun deleteTransaction(id: Int) {
        var db = writableDatabase

//        SQL Queries
        var sql = "DELETE FROM trans WHERE id='$id'"
        db.execSQL(sql)

//        In-build Functions
//        db.delete("trans", "id=$id", null)
    }

    fun updateTransaction(trans: TransModel) {
        var db = writableDatabase
        var values = ContentValues().apply {
            trans.apply {
                put("amt",amt)
                put("category",category)
                put("note",note)
                put("isexpense",isExpense)
            }
        }

        db.update("trans",values,"id=${trans.id}",null)

    }

}
