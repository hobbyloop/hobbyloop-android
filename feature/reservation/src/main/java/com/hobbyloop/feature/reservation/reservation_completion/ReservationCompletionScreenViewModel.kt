package com.hobbyloop.feature.reservation.reservation_completion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
class ReservationCompletionScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(), ContainerHost<CompletionState, CompletionSideEffect> {

    private val args = ReservationClassCompletionArgs(savedStateHandle)
    private val classId = args.classId

    override val container: Container<CompletionState, CompletionSideEffect> = container(
        initialState = CompletionState(),
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
                    name = "김지원",
                    phoneNumber = "01044255401"
                )
            }
        }
    }

    fun navigateToHomeScreen() = intent {
        postSideEffect(CompletionSideEffect.NavigateToReservationHome)
    }
}

@Immutable
data class CompletionState(
    val classId: String = "",
    val name: String = "",
    val phoneNumber: String = "",
)

sealed interface CompletionSideEffect {
    data object NavigateToReservationHome : CompletionSideEffect
}
