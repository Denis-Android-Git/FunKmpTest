package com.example.testpartnerkin.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testpartnerkin.domain.models.Conference
import com.example.testpartnerkin.domain.models.Image
import com.example.testpartnerkin.domain.models.Result
import com.example.testpartnerkin.domain.models.Type
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import testpartnerkin.composeapp.generated.resources.Res
import testpartnerkin.composeapp.generated.resources.hugeicons_customer_support
import testpartnerkin.composeapp.generated.resources.icon_Go_back

@Composable
fun MainScreen(
    confList: List<Result>,
    isLoading: Boolean,
    error: String?
) {

    val columnState = rememberLazyListState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().systemBarsPadding().padding(start = 16.dp, end = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(resource = Res.drawable.icon_Go_back),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Menu"
                )
                Text("Конференции", fontSize = 18.sp)
                Image(
                    painter = painterResource(resource = Res.drawable.hugeicons_customer_support),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Menu"
                )
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(it),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            }
            error?.let { message ->
                Text(
                    message,
                    modifier = Modifier.padding(16.dp),
                    fontSize = 18.sp
                )
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(it).padding(top = 16.dp),
            state = columnState,
            verticalArrangement = Arrangement.spacedBy(48.dp)
        ) {
            items(confList) { conference ->
                MainScreenListItem(conference = conference.conference)            }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview() {
    val confList = listOf(
        Result(
            conference = Conference(
                name = "Conference 1",
                categories = emptyList(),
                city = "Moscow",
                cityId = 1,
                country = "Russia",
                countryId = 1,
                customDate = "2024-03-15",
                endDate = "2024-03-17",
                format = "Hybrid",
                id = 1,
                image = Image(
                    height = 12,
                    id = "1",
                    placeholderColor = null,
                    preview = "",
                    url = "",
                    width = 20
                ),
                oneday = 1,
                rating = null,
                startDate = "2024-03-15",
                status = "active",
                statusTitle = "Active",
                type = Type(
                    id = 1,
                    name = ""
                ),
                typeId = 1,
                url = "https://example.com/conference1"
            ),
            viewType = 1
        ),
        Result(
            conference = Conference(
                name = "Conference 2",
                categories = emptyList(),
                city = "Moscow",
                cityId = 1,
                country = "Russia",
                countryId = 1,
                customDate = "2024-03-15",
                endDate = "2024-03-17",
                format = "Hybrid",
                id = 1,
                image = Image(
                    height = 12,
                    id = "1",
                    placeholderColor = null,
                    preview = "",
                    url = "",
                    width = 20
                ),
                oneday = 1,
                rating = null,
                startDate = "2024-03-15",
                status = "active",
                statusTitle = "Active",
                type = Type(
                    id = 1,
                    name = ""
                ),
                typeId = 1,
                url = "https://example.com/conference1"
            ),
            viewType = 1
        ),
    )
    MainScreen(
        confList = confList,
        isLoading = false,
        error = null
    )
}

