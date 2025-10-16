package com.example.testpartnerkin.presentation.ui

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.testpartnerkin.core.calculateDaysBetween
import com.example.testpartnerkin.core.convertDate
import com.example.testpartnerkin.domain.models.Category
import com.example.testpartnerkin.domain.models.DetailsData
import com.example.testpartnerkin.domain.models.Image
import com.example.testpartnerkin.domain.models.Type
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import testpartnerkin.composeapp.generated.resources.Icon_Go_back2
import testpartnerkin.composeapp.generated.resources.Res
import testpartnerkin.composeapp.generated.resources.icon_Go_back

@Composable
fun DetailScreen(
    detailsData: DetailsData?,
    isLoading: Boolean,
    error: String?,
    onBackClick: () -> Unit
) {

    val blueColor = Color(0xFF456AEF)
    val darkBlue = Color(0xFF0E1234)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().systemBarsPadding().padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = onBackClick) {
                    Image(
                        painter = painterResource(Res.drawable.icon_Go_back),
                        modifier = Modifier
                            .size(24.dp),
                        contentDescription = "Назад"
                    )
                }
            }
        }
    ) { paddingValues ->
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            detailsData != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = detailsData.type.name,
                        modifier = Modifier.padding(start = 16.dp, top = 12.dp),
                        fontSize = 14.sp,
                        color = darkBlue
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = detailsData.name,
                        modifier = Modifier.padding(start = 16.dp),
                        fontSize = 24.sp,
                        color = darkBlue
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    AsyncImage(
                        model = detailsData.image.url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyRow(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(detailsData.categories) {
                            TagChip(it.name)
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        InfoRow(
                            icon = Icons.Default.DateRange,
                            text = "${convertDate(detailsData.startDate)}, ${calculateDaysBetween(detailsData.startDate, detailsData.endDate)} дня",
                            color = blueColor
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        InfoRow(
                            icon = Icons.Outlined.LocationOn,
                            text = "${detailsData.city}, ${detailsData.country}",
                            color = blueColor
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blueColor
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Регистрация",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Связанные события",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkBlue
                        )
                        Spacer(modifier = Modifier.height(24.dp))

                        RelatedEventItem(
                            title = "ЛАС-ВЕГАС ЯНВ '24",
                            isNew = true,
                            rating = null,
                            color = blueColor
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider(color = Color(0xFFEFF2F9), thickness = 1.dp)
                        RelatedEventItem(
                            title = "ЛАС-ВЕГАС ЯНВ '24",
                            isNew = false,
                            rating = "5",
                            color = blueColor
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        HorizontalDivider(color = Color(0xFFEFF2F9), thickness = 1.dp)
                        RelatedEventItem(
                            title = "ЛАС-ВЕГАС ЯНВ '23",
                            isNew = false,
                            rating = "8.3",
                            color = blueColor
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "О событии",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = darkBlue
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = detailsData.about,
                            fontSize = 15.sp,
                            lineHeight = 20.sp,
                            color = darkBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Ключевые возможности, которые предоставляет EDCON 2024:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkBlue
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        BulletPoint(
                            "программа, нацеленная на удовлетворение ваших потребностей и интересов;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "лучшие спикеры — знания и опыт экспертов помогут погрузиться в мир Ethereum глубже;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "глобальная аудитория — взаимодействие с участниками мероприятия, включая 20 тысяч посетителей;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "общественные мероприятия — участие в демонстрационном дне и мастер-классах, чтобы еще глубже погрузиться в мир Ethereum.",
                            color = darkBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = "Кому будет полезна EDCON 2024:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = darkBlue
                        )
                        Spacer(modifier = Modifier.height(12.dp))

                        BulletPoint(
                            "разработчики и программисты, интересующиеся блокчейном и Ethereum;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "предприниматели, исследующие возможности в области децентрализованных приложений;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "инвесторы, ищущие новые перспективы в индустрии криптовалют;",
                            color = darkBlue
                        )
                        BulletPoint(
                            "активисты, стремящиеся к развитию сообщества Ethereum.",
                            color = darkBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Приглашаем вас присоединиться к EDCON 2024 и стать частью коллективного стремления реализовать потенциал Ethereum!",
                        fontSize = 15.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = darkBlue
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}

@Composable
fun TagChip(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun InfoRow(icon: ImageVector, text: String, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}

@Composable
fun RelatedEventItem(title: String, isNew: Boolean, rating: String?, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isNew) {
                Box(
                    modifier = Modifier
                        .width(34.dp)
                        .height(18.dp)
                        .background(
                            color = Color(0xFFEFF2F9),
                            shape = RoundedCornerShape(6.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "New",
                        fontSize = 10.sp,
                        color = color
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = title,
                fontSize = 14.sp,
                color = if (isNew) color else Color.Black
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (rating != null) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = Color(0xFFFFD700),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = rating,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(Res.drawable.Icon_Go_back2),
                contentDescription = null,
                tint = if (isNew) color else Color.Gray,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun BulletPoint(text: String, color: Color) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = " • ",
            fontSize = 15.sp,
            color = color
        )
        Text(
            text = text,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            modifier = Modifier.weight(1f),
            color = color
        )
    }
}

@Composable
@Preview
fun DetailScreenPreview() {
    val data = DetailsData(
        about = "EDCON 2024 представляет собой событие, которое объединяет спикеров высшего уровня и предлагает релевантный контент для участников. Это не просто конференция — это возможность погрузиться в мир Ethereum и встретиться с представителями лучшего сообщества в этой сфере.",
        categories = listOf(
            Category(id = 1, name = "Blockchain", url = "https://example.com/blockchain"),
            Category(id = 2, name = "Crypto", url = "https://example.com/crypto")
        ),
        city = "Дубай",
        cityId = 1,
        country = "ОАЭ",
        countryId = 1,
        customDate = null,
        endDate = "2024-10-24",
        format = "Офлайн",
        id = 1,
        image = Image(
            height = 300,
            id = "1",
            placeholderColor = null,
            preview = "",
            url = "https://via.placeholder.com/400x200/456AEF/FFFFFF?text=Blockchain+Life+2024",
            width = 400
        ),
        name = "Blockchain Life 2024",
        oneday = 0,
        rating = "4.8",
        registerUrl = "https://blockchain-life.com/register",
        startDate = "2024-10-22",
        status = "active",
        statusTitle = "Активна",
        type = Type(id = 1, name = "Blockchain"),
        typeId = 1,
        url = "https://blockchain-life.com"
    )
    DetailScreen(
        detailsData = data,
        isLoading = false,
        error = null,
        onBackClick = {}
    )
}

@Composable
@Preview
fun DetailScreenLoadingPreview() {
    val data = DetailsData(
        about = "EDCON 2024 представляет собой событие, которое объединяет спикеров высшего уровня и предлагает релевантный контент для участников. Это не просто конференция — это возможность погрузиться в мир Ethereum и встретиться с представителями лучшего сообщества в этой сфере.",
        categories = listOf(
            Category(id = 1, name = "Blockchain", url = "https://example.com/blockchain"),
            Category(id = 2, name = "Crypto", url = "https://example.com/crypto")
        ),
        city = "Дубай",
        cityId = 1,
        country = "ОАЭ",
        countryId = 1,
        customDate = null,
        endDate = "2024-10-24",
        format = "Офлайн",
        id = 1,
        image = Image(
            height = 300,
            id = "1",
            placeholderColor = null,
            preview = "",
            url = "https://via.placeholder.com/400x200/456AEF/FFFFFF?text=Blockchain+Life+2024",
            width = 400
        ),
        name = "Blockchain Life 2024",
        oneday = 0,
        rating = "4.8",
        registerUrl = "https://blockchain-life.com/register",
        startDate = "2024-10-22",
        status = "active",
        statusTitle = "Активна",
        type = Type(id = 1, name = "Blockchain"),
        typeId = 1,
        url = "https://blockchain-life.com"
    )
    DetailScreen(
        detailsData = data,
        isLoading = true,
        error = null,
        onBackClick = {}
    )
}

@Composable
@Preview
fun DetailScreenErrorPreview() {
    val data = DetailsData(
        about = "EDCON 2024 представляет собой событие, которое объединяет спикеров высшего уровня и предлагает релевантный контент для участников. Это не просто конференция — это возможность погрузиться в мир Ethereum и встретиться с представителями лучшего сообщества в этой сфере.",
        categories = listOf(
            Category(id = 1, name = "Blockchain", url = "https://example.com/blockchain"),
            Category(id = 2, name = "Crypto", url = "https://example.com/crypto")
        ),
        city = "Дубай",
        cityId = 1,
        country = "ОАЭ",
        countryId = 1,
        customDate = null,
        endDate = "2024-10-24",
        format = "Офлайн",
        id = 1,
        image = Image(
            height = 300,
            id = "1",
            placeholderColor = null,
            preview = "",
            url = "https://via.placeholder.com/400x200/456AEF/FFFFFF?text=Blockchain+Life+2024",
            width = 400
        ),
        name = "Blockchain Life 2024",
        oneday = 0,
        rating = "4.8",
        registerUrl = "https://blockchain-life.com/register",
        startDate = "2024-10-22",
        status = "active",
        statusTitle = "Активна",
        type = Type(id = 1, name = "Blockchain"),
        typeId = 1,
        url = "https://blockchain-life.com"
    )
    DetailScreen(
        isLoading = false,
        error = "Ошибка загрузки данных",
        detailsData = data,
        onBackClick = {}
    )
}