package datasource.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import model.point.PointTotalHistoryResponse

interface PointDataSource {
    suspend fun getPointTotalHistory() : CustomResult<PointTotalHistoryResponse, DataError.Network>
}