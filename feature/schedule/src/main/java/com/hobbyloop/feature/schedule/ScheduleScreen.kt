package com.hobbyloop.feature.schedule

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hobbyloop.core.ui.componenet.reservation.ticket.TicketCard
import com.hobbyloop.core.ui.componenet.reservation.top_bar.ReservationNavBar
import com.hobbyloop.core.ui.componenet.yearly_calendar.YearlyReservationCalendar
import com.hobbyloop.core.ui.util.TextUtil.toTicketInfoFormattedString
import com.hobbyloop.domain.entity.calendar.DaySelected
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun ScheduleScreen(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val state = viewModel.collectAsState().value
    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ScheduleSideEffect.ExceptionHandler -> {
                Log.d("ScheduleScreenException", sideEffect.message)
            }
        }
    }

    ScheduleScreen(
        classInfoList = state.classInfoList
    )
}

@Composable
internal fun ScheduleScreen(
    classInfoList: List<Pair<Instructor, List<ClassInfo>>>,
) {
    Scaffold(
        topBar = {
            ReservationNavBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .padding(17.dp),
                onSearchClick = { },
                onNotificationClick = { },
            )
        },
        containerColor = Color.White
    ) { padding ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            YearlyReservationCalendar(
                classData = classInfoList,
                modifier = Modifier
                    .fillMaxSize()
            ) { daySelected: DaySelected ->
                Crossfade(
                    targetState = daySelected.classInfoList,
                    animationSpec = tween(durationMillis = 1000),
                    label = "daySelectedChangeAnimation"
                ) { classInfoList: List<Pair<Instructor, List<ClassInfo>>>? ->
                    Column {
                        if (classInfoList == null) {
                            Spacer(modifier = Modifier.height(200.dp))
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .animateContentSize(animationSpec = tween(durationMillis = 1000)),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                classInfoList.forEachIndexed { index, (instructor, classInfoList) ->
                                    classInfoList.forEachIndexed { classIndex, classInfo ->
                                        TicketCard(
                                            centerIconImageUrl = instructor.imageUrl,
                                            classTitle = classInfo.title,
                                            centerName = instructor.name,
                                            instructorName = instructor.name,
                                            classTime = classInfo.dateTime.toTicketInfoFormattedString(),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(125.dp)
                                                .padding(horizontal = 16.dp)
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(76.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewScheduleScreen() {
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
            ClassInfo(9, "2024-06-16 09:00 - 10:00", "바디 피트", "중급", 8, 10),
            ClassInfo(10, "2024-06-14 07:30 - 08:30", "명상 클래스", "초급", 15, 15),
            ClassInfo(11, "2024-06-21 18:00 - 19:00", "이브닝 요가", "중급", 9, 10)
        ),
        listOf(
            ClassInfo(1, "2024-06-5 08:00 - 09:00", "아침 요가", "초급", 10, 13),
            ClassInfo(5, "2024-06-10 08:00 - 09:00", "필라테스 입문", "초급", 8, 10),
            ClassInfo(6, "2024-06-10 11:00 - 12:00", "중급 필라테스", "중급", 6, 7),
            ClassInfo(7, "2024-06-12 08:00 - 09:00", "필라테스 입문", "초급", 7, 9),
            ClassInfo(8, "2024-06-12 11:00 - 12:00", "중급 필라테스", "중급", 4, 4),
            ClassInfo(12, "2024-06-16 12:00 - 13:00", "고급 필라테스", "고급", 5, 5),
            ClassInfo(13, "2024-06-16 15:00 - 16:00", "건강 요가", "초급", 10, 12),
            ClassInfo(14, "2024-06-21 10:00 - 11:00", "통증 관리 요가", "중급", 7, 8)
        )
    )

    ScheduleScreen(
        classInfoList = instructors.zip(classes)
    )
}
