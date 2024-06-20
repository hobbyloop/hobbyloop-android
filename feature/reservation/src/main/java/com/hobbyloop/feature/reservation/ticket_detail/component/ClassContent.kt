package com.hobbyloop.feature.reservation.ticket_detail.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hobbyloop.ui.R
import com.hobbyloop.core.ui.componenet.box.OutlinedGradientBox
import com.hobbyloop.core.ui.util.TextUtil.extractTime
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import theme.HobbyLoopColor

@Composable
fun ClassContent(
    instructorWithClasses: Pair<Instructor, List<ClassInfo>>,
    selectedClassInfoState: ClassInfo?,
    onExpandBottomSheetClick: () -> Unit,
    onSelectClassInfo: (ClassInfo) -> Unit,
    onSelectWaitClassInfo: (ClassInfo) -> Unit,
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // 소제목 가능수업에 대한 컴포저블
        ClassesTitle()

        Spacer(modifier = Modifier.width(8.dp))

        // 수업 시간 + 정보 박스 컴포저블
        ClassList(
            classList = instructorWithClasses.second,
            selectedClassInfoState = selectedClassInfoState,
            onExpandBottomSheetClick = onExpandBottomSheetClick,
            onSelectClassInfo = onSelectClassInfo,
            onSelectWaitClassInfo = onSelectWaitClassInfo
        )
    }
}

@Composable
fun ClassesTitle() {
    Row {
        Icon(
            painter = painterResource(id = R.drawable.ic_time_color),
            contentDescription = "가능수업 아이콘",
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "가능수업",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ClassList(
    classList: List<ClassInfo>,
    selectedClassInfoState: ClassInfo?,
    onExpandBottomSheetClick: () -> Unit,
    onSelectClassInfo: (ClassInfo) -> Unit,
    onSelectWaitClassInfo: (ClassInfo) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        AnimatedContent(
            targetState = classList,
            transitionSpec = {
                (fadeIn(animationSpec = tween(600)) togetherWith fadeOut(animationSpec = tween(600)))
                    .using(SizeTransform(clip = false))
            },
            label = "수업 시간, 수업 정보 애니메이션"
        ) { list ->
            Column {
                list.forEach { classInfo ->
                    // 수업 시간 컴포저블
                    ClassTimeRow(classInfo)

                    Spacer(modifier = Modifier.height(16.dp))

                    // 수업 정보 컴포저블
                    ClassInfoBox(
                        classInfo = classInfo,
                        isSelected = selectedClassInfoState?.classId == classInfo.classId,
                        onExpandBottomSheetClick = { onExpandBottomSheetClick() },
                        onSelect = { onSelectClassInfo(classInfo) },
                        onSelectWaitClassInfo = { onSelectWaitClassInfo(classInfo) }
                    )
                }
            }
        }
    }
}

@Composable
fun ClassInfoBox(
    classInfo: ClassInfo,
    isSelected: Boolean,
    onExpandBottomSheetClick: () -> Unit,
    onSelect: () -> Unit,
    onSelectWaitClassInfo: () -> Unit,
) {
    OutlinedGradientBox(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        borderWidth = 2.dp,
        gradientColors = listOf(
            Color(0xFF9EB6FC),
            Color(0xFFFFAAAA)
        ),
        borderShape = RoundedCornerShape(
            topStart = 30.dp,
            topEnd = 8.dp,
            bottomEnd = 8.dp,
            bottomStart = 8.dp
        ),
        isSelectedColorAvailable = true,
        isSelected = isSelected,
        unSelectedColor = HobbyLoopColor.Gray20,
        onClick = onSelect
    ) {
        if (classInfo.fullReservationCount == classInfo.currentReservationCount) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 26.dp, top = 8.dp)
                    .background(
                        color = HobbyLoopColor.Primary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            onExpandBottomSheetClick()
                            onSelectWaitClassInfo()
                        }
                    ),
            ) {
                Text(
                    text = "대기가능",
                    color = HobbyLoopColor.Primary,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(vertical = 18.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = classInfo.title,
                modifier = Modifier.padding(start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Row(
                modifier = Modifier.padding(start = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "수업 난이도:",
                    fontSize = 14.sp
                )
                Text(
                    text = classInfo.difficulty,
                    color = HobbyLoopColor.Primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(HobbyLoopColor.Gray40)
            )

            Row(
                modifier = Modifier.padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user_color),
                    contentDescription = "유저 아이콘"
                )

                Spacer(modifier = Modifier.width(6.dp))

                Text(text = "예약")

                Spacer(modifier = Modifier.width(6.dp))

                Text(
                    text = "${classInfo.currentReservationCount}",
                    color = HobbyLoopColor.Primary,
                    fontSize = 14.sp
                )

                Text(
                    text = "/${classInfo.fullReservationCount}명",
                    fontSize = 14.sp
                )
            }
        }
    }

    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ClassTimeRow(classInfo: ClassInfo) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = com.hobbyloop.ui.R.drawable.ic_dot),
            tint = HobbyLoopColor.Primary,
            contentDescription = "가능한 시간 표시 점",
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(text = extractTime(classInfo.dateTime))
    }
}

@Preview(showBackground = true)
@Composable
fun ClassesTitlePreview() {
    ClassesTitle()
}

@Preview(showBackground = true)
@Composable
fun ClassTimeRowPreview() {
    val classInfo = ClassInfo(
        classId = 1,
        title = "Sample Class",
        difficulty = "Beginner",
        dateTime = "2024-05-7 08:00 - 09:00",
        currentReservationCount = 5,
        fullReservationCount = 10
    )
    ClassTimeRow(classInfo)
}

@Preview(showBackground = true)
@Composable
fun ClassInfoBoxPreview() {
    val classInfo = ClassInfo(
        classId = 1,
        title = "Sample Class",
        difficulty = "Beginner",
        dateTime = "2023-05-21T10:00:00",
        currentReservationCount = 5,
        fullReservationCount = 10
    )
    ClassInfoBox(
        classInfo = classInfo,
        isSelected = true,
        onExpandBottomSheetClick = {},
        onSelect = {},
        onSelectWaitClassInfo = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ClassListPreview() {
    val classInfoList = listOf(
        ClassInfo(
            classId = 1,
            title = "Sample Class 1",
            difficulty = "Beginner",
            dateTime = "2023-05-21T10:00:00",
            currentReservationCount = 5,
            fullReservationCount = 10
        ),
        ClassInfo(
            classId = 2,
            title = "Sample Class 2",
            difficulty = "Intermediate",
            dateTime = "2023-05-21T12:00:00",
            currentReservationCount = 3,
            fullReservationCount = 10
        )
    )
    ClassList(
        classList = classInfoList,
        selectedClassInfoState = classInfoList[0],
        onExpandBottomSheetClick = {},
        onSelectClassInfo = {},
        onSelectWaitClassInfo = {}
    )
}

@Preview(showBackground = true)
@Composable
fun ClassContentPreview() {
    val instructor = Instructor(
        name = "John Doe",
        imageUrl = "https://example.com/johndoe.jpg",
        history = listOf("10 years experience", "Expert in Yoga")
    )
    val classInfoList = listOf(
        ClassInfo(
            classId = 1,
            title = "Sample Class 1",
            difficulty = "Beginner",
            dateTime = "2023-05-21T10:00:00",
            currentReservationCount = 5,
            fullReservationCount = 10
        ),
        ClassInfo(
            classId = 2,
            title = "Sample Class 2",
            difficulty = "Intermediate",
            dateTime = "2023-05-21T12:00:00",
            currentReservationCount = 3,
            fullReservationCount = 10
        )
    )
    val instructorWithClasses = Pair(instructor, classInfoList)

    ClassContent(
        instructorWithClasses = instructorWithClasses,
        selectedClassInfoState = classInfoList[0],
        onExpandBottomSheetClick = {},
        onSelectClassInfo = {},
        onSelectWaitClassInfo = {}
    )
}

