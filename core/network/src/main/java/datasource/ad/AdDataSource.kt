package datasource.ad

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import model.ad.AdResponse

interface AdDataSource {
    fun getAds(): Flow<CustomResult<List<AdResponse>, DataError.Network>>
}