package datasource.ad

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.ad.AdResponse
import javax.inject.Inject

class MockAdDataSourceImpl @Inject constructor() : AdDataSource {

    private val ads = listOf(
        AdResponse(1, 1, "헤라1 필라테스", "필라테스 스튜디오", "5월 얼리버드 수강생 모집중!", "필라테스", "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg", "2023-03-01", "2023-03-15", 1),
        AdResponse(2, 2, "헤라2 필라테스", "필라테스 스튜디오", "5월 얼리버드 수강생 모집중!", "필라테스", "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg", "2023-03-01", "2023-03-15", 1),
        AdResponse(3, 3, "헤라3 필라테스", "필라테스 스튜디오", "5월 얼리버드 수강생 모집중!", "필라테스", "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg", "2023-03-01", "2023-03-15", 1),
        AdResponse(4, 4, "헤라4 필라테스", "필라테스 스튜디오", "5월 얼리버드 수강생 모집중!", "필라테스", "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg", "2023-03-01", "2023-03-15", 1),
        // Add more mock ads if needed
    )

    override fun getAds(): Flow<CustomResult<List<AdResponse>, DataError.Network>> = flow {
        delay(1000)
//        val result: CustomResult<List<AdResponse>, DataError.Network> = when (Random.nextInt(2)) {
//            0 -> CustomResult.Success(ads)
//            1 -> CustomResult.Error(DataError.Network.UNAUTHORIZED)
//            else -> CustomResult.Error(DataError.Network.SERVER_ERROR)
//        }

        val result: CustomResult<List<AdResponse>, DataError.Network> = CustomResult.Success(ads)

        emit(result)
    }
}