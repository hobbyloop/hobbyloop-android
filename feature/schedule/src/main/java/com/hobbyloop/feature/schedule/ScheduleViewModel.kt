package com.hobbyloop.feature.schedule

import androidx.lifecycle.ViewModel
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor() : ViewModel(),
    ContainerHost<ScheduleState, ScheduleSideEffect> {
    override val container: Container<ScheduleState, ScheduleSideEffect> = container(
        initialState = ScheduleState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                // TODO 추후 에러 핸들링
                intent { postSideEffect(ScheduleSideEffect.ExceptionHandler(message = throwable.message.orEmpty())) }
            }
        }
    )

    init {
        blockingIntent {
            delay(5)
            reduce {
                /**
                 * TODO 더미값으로 초기값 설정, 추후 네트워크 통신을 통해 데이터를 가져와 할당해야함
                 */
                state.copy(
                    classInfoList = createDummyData()
                )
            }
        }
    }

    private fun createDummyData(): List<Pair<Instructor, List<ClassInfo>>> {
        val instructors = listOf(
            Instructor(
                "윤지영",
                "https://src.hidoc.co.kr/image/lib/2022/3/28/1648455838120_0.jpg",
                listOf("2급 스포츠 지도사", "2급 스포츠 지도사", "2급 스포츠 지도사", "2급 스포츠 지도사")
            ),
            Instructor(
                "김지영",
                "https://exbody.kr/wp-content/uploads/2022/05/%ED%95%84%EB%9D%BC%ED%85%8C%EC%8A%A4-%EC%A0%95%EC%9D%98-%EC%97%AD%EC%82%AC-1-1536x1024.jpg",
                listOf("1급 스포츠 지도사", "1급 스포츠 지도사", "1급 스포츠 지도사", "1급 스포츠 지도사")
            )
        )

        val classes = listOf(
            listOf(
                ClassInfo(20, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(21, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(22, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(23, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(24, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(1, "2024-06-7 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(2, "2024-06-7 10:00 - 11:00", "고급 요가", "고급", 5, 5),
                ClassInfo(3, "2024-06-12 08:00 - 09:00", "아침 요가", "초급", 12, 12),
                ClassInfo(4, "2024-06-12 10:00 - 11:00", "고급 요가", "고급", 3, 6),
                ClassInfo(9, "2024-06-14 09:00 - 10:00", "바디 피트", "중급", 8, 10),
                ClassInfo(10, "2024-06-17 07:30 - 08:30", "명상 클래스", "초급", 15, 15),
                ClassInfo(11, "2024-06-21 18:00 - 19:00", "이브닝 요가", "중급", 9, 10)
            ),
            listOf(
                ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
                ClassInfo(5, "2024-06-10 08:00 - 09:00", "필라테스 입문", "초급", 8, 10),
                ClassInfo(6, "2024-06-10 11:00 - 12:00", "중급 필라테스", "중급", 6, 7),
                ClassInfo(7, "2024-06-12 08:00 - 09:00", "필라테스 입문", "초급", 7, 9),
                ClassInfo(8, "2024-06-12 11:00 - 12:00", "중급 필라테스", "중급", 4, 4),
                ClassInfo(12, "2024-06-17 12:00 - 13:00", "고급 필라테스", "고급", 5, 5),
                ClassInfo(13, "2024-06-17 15:00 - 16:00", "건강 요가", "초급", 10, 12),
                ClassInfo(14, "2024-06-21 10:00 - 11:00", "통증 관리 요가", "중급", 7, 8)
            )
        )

        return instructors.zip(classes)
    }
}

@Immutable
data class ScheduleState(
    val classInfoList: List<Pair<Instructor, List<ClassInfo>>> = emptyList()
)

sealed interface ScheduleSideEffect {
    class ExceptionHandler(val message: String) : ScheduleSideEffect
}

