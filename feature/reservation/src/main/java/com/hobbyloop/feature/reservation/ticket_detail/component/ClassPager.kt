package com.hobbyloop.feature.reservation.ticket_detail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.hobbyloop.feature.reservation.Gray40
import com.hobbyloop.feature.reservation.Purple
import com.hobbyloop.feature.reservation.R
import com.hobbyloop.feature.reservation.model.ClassInfo
import com.hobbyloop.feature.reservation.model.Instructor

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ClassPager(
    classInfo: List<Pair<Instructor, List<ClassInfo>>>,
    onResetInstructorDetailsVisible: () -> Unit = {},
    onClassInfoContentChanged: @Composable (Pair<Instructor, List<ClassInfo>>) -> Unit
) {
    val imageUrls = classInfo.map { it.first.imageUrl }
    val pagerState = rememberPagerState(pageCount = {
        imageUrls.size
    })

    LaunchedEffect(pagerState.currentPage) {
        onResetInstructorDetailsVisible()
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            AsyncImage(
                model = imageUrls[page],
                contentDescription = "Slider Image $page",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                placeholder = painterResource(id = R.drawable.loading_ic),
                error = painterResource(id = R.drawable.calendar_ic)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            repeat(imageUrls.size) { index ->
                val isSelected = index == pagerState.currentPage
                Box(
                    modifier = Modifier
                        .size(if (isSelected) 12.dp else 8.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) Purple else Gray40)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        if (pagerState.currentPage < imageUrls.size) {
            onClassInfoContentChanged(classInfo[pagerState.currentPage])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PagerWithIndicatorPreview() {
    // 더미 데이터
    val dummyClassInfo = listOf(
        Pair(
            Instructor("윤지영", "https://example.com/image1.jpg", listOf("1급 스포츠 지도사")),
            listOf(
                ClassInfo(1, "2024-05-11 08:00 - 09:00", "아침 요가", "초급", 10, 12),
                ClassInfo(2, "2024-05-11 10:00 - 11:00", "고급 요가", "고급", 5, 5)
            )
        ),
        Pair(
            Instructor("김지영", "https://example.com/image2.jpg", listOf("2급 스포츠 지도사")),
            listOf(
                ClassInfo(3, "2024-05-12 08:00 - 09:00", "필라테스 입문", "초급", 8, 10),
                ClassInfo(4, "2024-05-12 10:00 - 11:00", "중급 필라테스", "중급", 6, 6)
            )
        )
    )

    // 메인 컴포저블 함수 호출
    ClassPager(
        classInfo = dummyClassInfo,
        onResetInstructorDetailsVisible = {},
        onClassInfoContentChanged = {}
    )
}
