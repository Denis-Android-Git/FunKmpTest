package com.example.testpartnerkin.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.testpartnerkin.domain.models.Category
import com.example.testpartnerkin.domain.models.Conference
import com.example.testpartnerkin.domain.models.Image
import com.example.testpartnerkin.domain.models.Type
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import testpartnerkin.composeapp.generated.resources.Res
import testpartnerkin.composeapp.generated.resources.canceled

@Composable
fun MainScreenListItem(
    conference: Conference,
    onClick: (Int) -> Unit
) {

    val place by remember {
        mutableStateOf("${conference.city}, ${conference.country}")
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = createDate(conference.startDate), modifier = Modifier.padding(start = 16.dp), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth().background(
                color = if (conference.statusTitle == "Отменена") Color(0xFFFF3333).copy(alpha = 0.1f) else Color(0xFFEFF2F9),
                shape = RoundedCornerShape(16.dp)
            )
                .clickable {
                    onClick(conference.id)
                }
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            if (conference.statusTitle == "Отменена") {
                androidx.compose.foundation.Image(
                    painter = painterResource(
                        Res.drawable.canceled
                    ),
                    modifier = Modifier.height(24.dp).width(90.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(26.dp))
            }
            Text(text = conference.name, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth().height(104.dp).background(
                    color = if (conference.statusTitle == "Отменена") Color(0xFFFF3333).copy(alpha = 0.1f) else Color(0xFF456AEF).copy(alpha = 0.1f),
                    shape = RoundedCornerShape(12.dp)
                ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = conference.image.url,
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = takeDay(conference.startDate), fontSize = 40.sp)
                            Text(
                                text = fromDigitToEngMonth(conference.startDate), fontSize = 12.sp,
                                color = Color(0xFF0E1234)
                            )
                        }
                        Text("-", fontSize = 40.sp)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = takeDay(conference.endDate), fontSize = 40.sp)
                            Text(
                                text = fromDigitToEngMonth(conference.endDate), fontSize = 12.sp,
                                color = Color(0xFF0E1234)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(conference.categories) { category ->
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(60.dp)
                            )
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = category.name, fontSize = 11.sp, color = Color(0xFF060A3C))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    modifier = Modifier.size(16.dp),
                    contentDescription = "Location"
                )
                Text(text = if (conference.format == "offline") place else conference.format, fontSize = 14.sp, color = Color(0xFF060A3C))
            }
        }
    }
}

private fun createDate(date: String): String {
    val list = date.split("-")
    val year = list[0]
    val month = fromDigitToMonth(date)
    return "$month, $year"
}

private fun takeDay(date: String): String {
    return date.substring(8, 10)
}

private fun fromDigitToEngMonth(date: String): String {
    val month = date.substring(5, 7)
    return when (month) {
        "01" -> "JAN"
        "02" -> "FEB"
        "03" -> "MAR"
        "04" -> "APR"
        "05" -> "MAY"
        "06" -> "JUN"
        "07" -> "JUL"
        "08" -> "AUG"
        "09" -> "SEP"
        "10" -> "OCT"
        "11" -> "NOV"
        "12" -> "DEC"
        else -> ""
    }
}

private fun fromDigitToMonth(date: String): String {
    val month = date.substring(5, 7)
    return when (month) {
        "01" -> "Январь"
        "02" -> "Февраль"
        "03" -> "Март"
        "04" -> "Апрель"
        "05" -> "Май"
        "06" -> "Июнь"
        "07" -> "Июль"
        "08" -> "Август"
        "09" -> "Сентябрь"
        "10" -> "Октябрь"
        "11" -> "Ноябрь"
        "12" -> "Декабоь"
        else -> ""
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenListItemPreview() {
    val conf = Conference(
        categories = listOf(
            Category(id = 1, name = "Marketing", url = "https://example.com/categories/marketing")
        ),
        city = "Moscow",
        cityId = 1,
        country = "Russia",
        countryId = 1,
        customDate = null,
        endDate = "2025-10-20",
        format = "Online",
        id = 1001,
        image = Image(
            height = 400,
            id = "img-1001",
            placeholderColor = null,
            preview = "https://example.com/images/conf-1001-preview.jpg",
            url = "https://example.com/images/conf-1001.jpg",
            width = 600
        ),
        name = "Partnerkin Summit",
        oneday = 0,
        rating = null,
        startDate = "2025-10-18",
        status = "active",
        statusTitle = "Отменена",
        type = Type(
            id = 1,
            name = "Affiliate"
        ),
        typeId = 1,
        url = "https://example.com/conferences/partnerkin-summit"
    )
    MainScreenListItem(
        conference = conf,
        onClick = {}
    )
}