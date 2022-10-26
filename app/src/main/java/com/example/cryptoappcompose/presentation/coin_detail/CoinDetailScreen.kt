package com.example.cryptoappcompose.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptoappcompose.data.remote.dto.TeamMember
import com.example.cryptoappcompose.domain.model.Coin
import com.example.cryptoappcompose.presentation.Screen
import com.example.cryptoappcompose.presentation.coin_detail.components.CoinTag
import com.example.cryptoappcompose.presentation.coin_detail.components.TeamListItem
import com.example.cryptoappcompose.presentation.coin_list.components.CoinListItem
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coinDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coinDetail.isActive) "active" else "inactive",
                            color = if (coinDetail.isActive) Color.Green else Color.Red,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f),
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,

                            )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = coinDetail.description, style = MaterialTheme.typography.body2)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Tags", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(20.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        coinDetail.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Team Members", style = MaterialTheme.typography.h3)
                    Spacer(modifier = Modifier.height(20.dp))

                }
                items(coinDetail.team) { teamMember: TeamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }

        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}