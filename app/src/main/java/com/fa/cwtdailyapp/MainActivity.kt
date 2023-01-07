package com.fa.cwtdailyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fa.cwtdailyapp.data.entities.Member
import com.fa.cwtdailyapp.ui.DailyDestination
import com.fa.cwtdailyapp.ui.DailyViewModel
import com.fa.cwtdailyapp.ui.theme.Color
import com.fa.cwtdailyapp.ui.theme.CwtDailyAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalCoroutinesApi::class)
     val viewModel by viewModels<DailyViewModel>()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val nav = rememberNavController()

            CwtDailyAppTheme {
                Scaffold() {
                    NavHost(nav, startDestination = DailyDestination.AttendingScreenPage) {

                        composable(route = DailyDestination.AttendingScreenPage) {
                            AttendanceScreen(
                                viewModel = viewModel,
                                listOfAllTeam = viewModel.getMemberTeamListFrom(),
                                onClickRow = {},
                                onContinueClick = {})
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Composable
    fun AttendanceScreen(
        viewModel: DailyViewModel,
        listOfAllTeam: ArrayList<MutableState<Member>>,
        onClickRow: () -> Unit,
        onContinueClick: () -> Unit
    ) {

        Column(
            Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {

            LazyColumn(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth()
            ) {

                itemsIndexed(listOfAllTeam) { index, item ->
                    AttendanceItemRow(item, index)
                }
            }

            Button(
                onClick = { onContinueClick.invoke() },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Primary100,
                    contentColor = androidx.compose.ui.graphics.Color.White,
                )
            ) {
                Text(text = "Continue", fontStyle = FontStyle.Normal, fontSize = 14.sp)
            }
        }
    }

    @Composable
    fun AttendanceItemRow(member: MutableState<Member>, index: Int) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Checkbox(
                checked = member.value.isAttending,
                onCheckedChange = { member.value.isAttending = !member.value.isAttending },
            )
            Text(
                text = member.value.name,
                fontStyle = FontStyle.Normal,
                fontSize = 14.sp
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        CwtDailyAppTheme {
            Greeting("Android")
        }
    }
}