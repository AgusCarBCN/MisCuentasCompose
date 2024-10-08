package com.blogspot.agusticar.miscuentasv2.main.data.database.repository

import com.blogspot.agusticar.miscuentasv2.main.data.database.dao.AccountDao
import com.blogspot.agusticar.miscuentasv2.main.data.database.entities.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AccountRepository @Inject constructor(private val accountDao:AccountDao) {


    // StateFlow que mantiene el estado actual de las cuentas
    //actualizándolo cada vez que se insertan, eliminan o actualizan cuentas
    private val _accountsFlow = MutableStateFlow<List<Account>>(emptyList())
    val accountsFlow: StateFlow<List<Account>> = _accountsFlow

    // Inicializar cuentas al crear el repositorio
    suspend fun initializeAccounts() {
        refreshAccounts()
    }

    // 1. Insertar una cuenta
    suspend fun insertAccount(account: Account) {
        accountDao.insertAccount(account)
        refreshAccounts()
    }

    // 2. Eliminar una cuenta específica
    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
        refreshAccounts()
    }

    // 3. Eliminar todas las cuentas
    suspend fun deleteAllAccounts() {
        accountDao.deleteAllAccounts()
        refreshAccounts()
    }

    // 4. Listar todas las cuentas
    suspend fun getAllAccounts(): List<Account> {
        return accountDao.getAllAccounts()
    }

    // 5. Obtener una cuenta específica por ID
    suspend fun getAccountById(accountId: Int): Account? {
        return accountDao.getAccountById(accountId)
    }

    // 6. Actualizar el nombre de una cuenta
    suspend fun updateAccountName(accountId: Int, newName: String) {
        accountDao.updateAccountName(accountId, newName)
        refreshAccounts()
    }

    // 7. Actualizar el balance de una cuenta
    suspend fun updateAccountBalance(accountId: Int, newBalance: Double) {
        accountDao.updateAccountBalance(accountId, newBalance)
        refreshAccounts()
    }

    // Método para actualizar el flujo de cuentas
    private suspend fun refreshAccounts() {
        _accountsFlow.value = accountDao.getAllAccounts()
    }
}