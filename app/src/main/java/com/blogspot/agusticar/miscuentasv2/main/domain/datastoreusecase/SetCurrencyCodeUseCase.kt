package com.blogspot.agusticar.miscuentasv2.main.domain.datastoreusecase

import com.blogspot.agusticar.miscuentasv2.main.data.datastore.preferences.repository.UserDataStoreRepository
import javax.inject.Inject

class SetCurrencyCodeUseCase @Inject constructor(private val repository: UserDataStoreRepository) {

    suspend operator fun invoke(newValue: String) {
        repository.setCurrencyCode(newValue)
    }
}