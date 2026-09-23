package com.example.madd_tutorial04

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityData : ViewModel() {
    private val _listMyTable = MutableLiveData<List<MyTable>>()
    private val _insertSuccess = MutableLiveData<Boolean>().apply { value = false }

    // Holds the selected item for editing in InsertRecordFragment
    val selectedItem = MutableLiveData<MyTable?>()

    val listMyTable: LiveData<List<MyTable>> = _listMyTable
    val insertSuccess: LiveData<Boolean> = _insertSuccess

    fun loadData(context: Context) {
        val repository = MyTableRepositories(MyDatabase.getInstance(context))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = repository.getAll()
                _listMyTable.postValue(data)
            } catch (e: Exception) {
                e.printStackTrace()
                _listMyTable.postValue(emptyList())
            }
        }
    }

    fun insertData(context: Context, myTable: MyTable) {
        val repository = MyTableRepositories(MyDatabase.getInstance(context))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.insert(myTable)
                loadData(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateData(context: Context, myTable: MyTable) {
        val repository = MyTableRepositories(MyDatabase.getInstance(context))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.update(myTable)
                loadData(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteData(context: Context, myTable: MyTable) {
        val repository = MyTableRepositories(MyDatabase.getInstance(context))
        CoroutineScope(Dispatchers.IO).launch {
            try {
                repository.delete(myTable)
                loadData(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setInsertSuccess(value: Boolean) {
        _insertSuccess.value = value
    }
}
