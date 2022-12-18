package com.kigya.unique.data.local.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.kigya.unique.data.dto.account.AccountType
import com.kigya.unique.utils.constants.PreferencesKeys
import com.kigya.unique.utils.extensions.mapToAccountType
import com.kigya.unique.utils.extensions.mapToString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSettings @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppSettingsSource {

    override fun isSignedIn(): Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IS_SIGNED_IN] ?: false
        }

    override suspend fun signIn() {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.IS_SIGNED_IN] = true
        }
    }

    override suspend fun setCurrentAccountType(accountType: AccountType) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ACCOUNT_TYPE] = accountType.mapToString()
        }
    }

    override fun getCurrentAccountType(): Flow<AccountType> =
        dataStore.data.map {
            it[PreferencesKeys.ACCOUNT_TYPE]?.mapToAccountType() ?: AccountType.STUDENT
        }


    override suspend fun saveParamsToDataStore(course: Int, group: Int, subgroup: Int) {
        dataStore.edit {
            it[PreferencesKeys.COURSE] = course
            it[PreferencesKeys.GROUP] = group
            it[PreferencesKeys.SUBGROUP] = subgroup
        }
    }

    override fun getParamsFromDataStore(): Flow<Triple<Int, Int, Int>> =
        dataStore.data.map {
            Triple(
                it[PreferencesKeys.COURSE] ?: 3,
                it[PreferencesKeys.GROUP] ?: 9,
                it[PreferencesKeys.SUBGROUP] ?: 0
            )
        }

}