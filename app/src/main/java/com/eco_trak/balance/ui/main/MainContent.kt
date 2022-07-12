package com.eco_trak.balance.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.MyButton
import com.eco_trak.balance.ui.components.MyDropDownMenu
import com.eco_trak.balance.ui.components.MyText
import com.eco_trak.balance.ui.theme.*
import com.eco_trak.balance.R

@Composable
fun DashBoard(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val padding: Dp = dimensionResource(id = R.dimen.MyWidgetPadding)

    Column(
        modifier = modifier
    ) {

        // Units and Tare
        Row {
            MyText(
                hint = "Units",
                text = "TON",
                Modifier.weight(1f)
            )
            Spacer(Modifier.weight(2f))
            MyText(
                hint = "TARE",
                text = viewModel.tare.toString(),
                Modifier.weight(2f)
            )
        }
        Spacer(modifier = Modifier.height(padding))

        // Progress bar
        Row(
            Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
        ) {
            val progressBarHeight = 20
            LinearProgressIndicator(
                progress = 0.7f,
                Modifier
                    .height(progressBarHeight.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(padding))
            Text(
                text = "35°",
                modifier = Modifier.align(Alignment.CenterVertically),
                fontSize = progressBarHeight.sp,
                color = MyProgressBarTextColor
            )
        }
        Spacer(modifier = Modifier.height(padding))

        // Current Weight
        MyText(
            hint = "Current Weight",
            text = viewModel.currentWeight.toString(),
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(padding))

        // Last Bucket, Bucket Count and Expected Load
        Row(
            Modifier.fillMaxWidth()
        ) {
            MyText(
                hint = "Last Bucket",
                text = viewModel.lastBucket.toString(),
                Modifier.weight(1f),
                color = Color(0xFF00B6FD)
            )
            Spacer(modifier = Modifier.width(padding))
            MyText(
                hint = "Bucket Count",
                text = viewModel.bucketCount.toString(),
                Modifier.weight(1f),
                color = Color(0xFF00B6FD)
            )
            Spacer(modifier = Modifier.width(padding))
            MyText(
                hint = "Expected Load",
                text = viewModel.expectedLoad.toString(),
                Modifier.weight(1f),
                color = Color(0xFFF78A06)
            )
        }
        Spacer(modifier = Modifier.height(padding))

        // Total Load
        MyText(
            hint = "Total Load",
            text = viewModel.totalLoad.toString(),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 32.sp
        )
    }
}

@Composable
fun ControlPanel(
    onMainMenu: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    val padding: Dp = dimensionResource(id = R.dimen.MyWidgetPadding)

    // Main Menu and Pause
    Column {
        Row {
            MyButton(
                text = "Main Menu",
                onClick = {
                    onMainMenu()
                },
                colors = listOf(MyMainMenuColor1, MyMainMenuColor2),
                Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(padding))
            MyButton(
                text = "Pause",
                onClick = {

                },
                colors = listOf(MyMainMenuColor1, MyMainMenuColor2),
                Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(padding))

        // User
        MyDropDownMenu(
            hint = stringResource(R.string.user),
            text = if (viewModel.selectedUser == null) null else viewModel.selectedUser!!.toString(),
            list = viewModel.users,
            getItemText = {
                it.toString()
            },
            onClick = {
                viewModel.updateUser(it)
            },
            colors = listOf(MyUserMenuColor1, MyUserMenuColor2),
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(padding))

        // Customer
        MyDropDownMenu(
            hint = stringResource(R.string.customer),
            text = if (viewModel.selectedCustomer == null) null else viewModel.selectedCustomer!!.toString(),
            list = viewModel.customers,
            getItemText = {
                it.toString()
            },
            onClick = { customer ->
                viewModel.updateCustomer(customer)
            },
            colors = listOf(MyCustomerMenuColor1, MyCustomerMenuColor2),
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(padding))

        // Vehicle
        MyDropDownMenu(
            hint = stringResource(R.string.vehicle),
            text = if (viewModel.selectedVehicle == null) null else viewModel.selectedVehicle!!.toString(),
            list = viewModel.vehicles,
            getItemText = {
                it.toString()
            },
            onClick = { vehicle ->
                viewModel.updateVehicle(vehicle)
            },
            colors = listOf(MyVehicleMenuColor1, MyVehicleMenuColor2),
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(padding))

        // Material
        MyDropDownMenu(
            hint = stringResource(R.string.material),
            text = if (viewModel.selectedMaterial == null) null else viewModel.selectedMaterial!!.toString(),
            list = viewModel.materials,
            getItemText = {
                it.toString()
            },
            onClick = { material ->
                viewModel.updateMaterial(material)
            },
            colors = listOf(MyMaterialMenuColor1, MyMaterialMenuColor2),
            Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    val padding: Dp = dimensionResource(id = R.dimen.MyWidgetPadding)

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Max)
        ) {
            MyButton(
                text = "Cancel Load",
                onClick = {
                },
                colors = listOf(Color(0xFF857E7F), Color(0xFF191919)),
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(padding))
            MyButton(
                text = "Cancel Last Bucket",
                onClick = {
                },
                colors = listOf(Color(0xFF857E7F), Color(0xFF191919)),
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(padding))
            MyButton(
                text = "Close Load",
                onClick = {
                },
                colors = listOf(Color(0xFF857E7F), Color(0xFF191919)),
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Spacer(modifier = Modifier.width(padding))
            MyButton(
                text = "Close/Print",
                onClick = {
                },
                colors = listOf(Color(0xFF857E7F), Color(0xFF191919)),
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
        }
    }
}

@Composable
fun MainContent(
    padding: PaddingValues,
    navigateToSettingsScreen: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.MyWidgetPadding)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        DashBoard()
        ControlPanel(
            onMainMenu = {
                navigateToSettingsScreen()
            }
        )
        BottomBar()
    }
}

@Preview(
    showBackground = true,
    widthDp = 500, heightDp = 800,
    backgroundColor = 0x010101,
    locale = "fr",
)
@Composable
fun MainContentPreview() {
    MainContent(
        padding = PaddingValues(dimensionResource(id = R.dimen.MyWidgetPadding)),
        navigateToSettingsScreen = {}
    )
}
