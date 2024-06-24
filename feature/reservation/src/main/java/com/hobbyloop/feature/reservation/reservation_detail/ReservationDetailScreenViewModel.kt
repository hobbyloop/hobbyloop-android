package com.hobbyloop.feature.reservation.reservation_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hobbyloop.core.ui.util.TextFieldUtil.isValidKoreanName
import com.hobbyloop.core.ui.util.TextFieldUtil.isValidPhoneNumber
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@OptIn(OrbitExperimental::class)
@HiltViewModel
class ReservationClassDetailScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel(), ContainerHost<ClassDetailState, ClassDetailSideEffect> {

    private val args = ReservationDetailArgs(savedStateHandle)
    private val classId = args.classId

    override val container: Container<ClassDetailState, ClassDetailSideEffect> = container(
        initialState = ClassDetailState(),
        buildSettings = {
            this.exceptionHandler = CoroutineExceptionHandler { _, throwable ->
                //TODO 추후 에러 핸들링
            }
        }
    )

    init {
        intent {
            reduce {
                /**
                 * TODO 더미값으로 초기값 설정, 추후 네트워크 통신을 통해 데이터를 가져와 할당해야함
                 */
                state.copy(
                    classId = classId,
                    centerIconImageUrl = "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fi.pinimg.com%2F736x%2Fab%2Fa3%2Fb7%2Faba3b7628d618fb91eca54bb7f4fe709.jpg&type=sc960_832",
                    classTitle = "6:1 체형교정 필라테스",
                    centerName = "필라피티 스튜디오",
                    instructorName = "이민주 강사님",
                    classTime = "2023. 5. 12 금 09:00 - 09:50",
                    notice = "5월 둘째주는 휴무이니 참고하여 이용에 차질 없으시길 바랍니다.",
                    centerImageUrl = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MTZfMTA0%2FMDAxNTU1NDE1NTAzNTgx.n4hiEiGSF91TMegRtR5o3SA1RZbE6S6SnrnTGNNunlMg.PJoW33HktJZos6K3ENRRpZs3cNdYgSYv_3ph6RzIDx8g.JPEG.bemine9_9%2F0405_2_3.jpg&type=sc960_832",
                    sessionCount = 10,
                    usagePeriod = "2023.04.20 - 2023.06.20"
                )
            }
        }
    }

    fun onNameChange(name: String) = blockingIntent {
        reduce {
            if (isValidKoreanName(name)) {
                state.copy(isNameValid = true)
            } else {
                state.copy(isNameValid = false)
            }
        }
        reduce {
            state.copy(name = name)
        }
    }

    fun onPhoneNumberChange(phoneNumber: String) = blockingIntent {
        reduce {
            if (isValidPhoneNumber(phoneNumber)) {
                state.copy(isPhoneNumberValid = true)
            } else {
                state.copy(isPhoneNumberValid = false)
            }
        }
        reduce {
            state.copy(phoneNumber = phoneNumber)
        }
    }

    fun navigateToReservationCompletion() = intent {
        postSideEffect(ClassDetailSideEffect.NavigateToReservationCompletion(classId = state.classId))
    }
}

@Immutable
data class ClassDetailState(
    val classId: String = "",
    val centerIconImageUrl: String = "",
    val classTitle: String = "",
    val centerName: String = "",
    val instructorName: String = "",
    val classTime: String = "",
    val notice: String = "",
    val centerImageUrl: String = "",
    val sessionCount: Int = -1,
    val usagePeriod: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val isNameValid: Boolean = true,
    val isPhoneNumberValid: Boolean = true,
)

sealed interface ClassDetailSideEffect {
    class NavigateToReservationCompletion(val classId: String) : ClassDetailSideEffect
}
