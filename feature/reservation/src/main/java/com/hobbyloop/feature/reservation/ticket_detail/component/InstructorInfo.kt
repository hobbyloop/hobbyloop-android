package com.hobbyloop.feature.reservation.ticket_detail.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import com.hobbyloop.domain.entity.class_info.ClassInfo
import com.hobbyloop.domain.entity.class_info.Instructor
import com.hobbyloop.ui.R
import theme.HobbyLoopColor.Gray100
import theme.HobbyLoopTypo

@Composable
fun InstructorInfo(
    instructorWithClasses: Pair<Instructor, List<ClassInfo>>,
    isInstructorDetailsVisibleState: Boolean,
    onToggleInstructorDetailsVisible: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_instructor_pila),
                    contentDescription = "강사 유형",
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(4.dp))
                AnimatedContent(
                    targetState = instructorWithClasses.first.name,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(600)) togetherWith fadeOut(
                            animationSpec = tween(
                                600
                            )
                        )
                    },
                    label = "InstructorNameAnimation"
                ) { name ->
                    Text(
                        text = "$name 강사",
                        style = HobbyLoopTypo.head18,
                        color = Gray100,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        lineHeight = 18.sp
                    )
                }
            }

            Row(
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onToggleInstructorDetailsVisible
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "강사 프로필",
                    style = HobbyLoopTypo.body14,
                )
                Icon(
                    painter = painterResource(id = if (isInstructorDetailsVisibleState) R.drawable.ic_up else R.drawable.ic_down),
                    contentDescription = "더 많은 정보 보기",
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(modifier = Modifier.height(7.dp))

        AnimatedVisibility(
            visible = isInstructorDetailsVisibleState,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {
            Column {
                instructorWithClasses.first.history.forEach { history ->
                    Row(
                        modifier = Modifier.padding(vertical = 7.dp)
                    ) {
                        Text(
                            text = "이력",
                            style = HobbyLoopTypo.body14.copy(fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(18.dp))
                        Text(
                            text = history,
                            style = HobbyLoopTypo.body14
                        )
                    }
                }
                Spacer(modifier = Modifier.height(17.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InstructorInfoPreview() {
    val sampleInstructor = Instructor(
        name = "Jane Doe",
        imageUrl = "https://example.com/janedoe.jpg",
        history = listOf("5 years experience", "Expert in Pilates", "Certified Yoga Instructor")
    )

    val sampleClasses = listOf(
        ClassInfo(
            classId = 1,
            title = "Morning Yoga",
            difficulty = "Beginner",
            dateTime = "2023-05-21T08:00:00",
            currentReservationCount = 10,
            fullReservationCount = 20
        ),
        ClassInfo(
            classId = 2,
            title = "Evening Pilates",
            difficulty = "Intermediate",
            dateTime = "2023-05-21T18:00:00",
            currentReservationCount = 15,
            fullReservationCount = 20
        )
    )

    InstructorInfo(
        instructorWithClasses = Pair(sampleInstructor, sampleClasses),
        isInstructorDetailsVisibleState = true,
        onToggleInstructorDetailsVisible = {}
    )
}

