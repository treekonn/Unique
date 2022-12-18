package com.kigya.unique.data.local.settings

import com.kigya.unique.data.dto.account.AccountType
import kotlinx.coroutines.flow.Flow

interface AppSettingsSource {

    fun isSignedIn(): Flow<Boolean>

    suspend fun signIn()

    suspend fun setCurrentAccountType(accountType: AccountType)

    fun getCurrentAccountType(): Flow<AccountType>

    suspend fun saveParamsToDataStore(course: Int, group: Int, subgroup: Int = 0)

    fun getParamsFromDataStore(): Flow<Triple<Int, Int, Int>>

}