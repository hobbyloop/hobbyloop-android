package datasource.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import model.point.PointResponse

interface PointDataSource {
    fun getPointHistory() : Flow<CustomResult<PointResponse, DataError.Network>>
}