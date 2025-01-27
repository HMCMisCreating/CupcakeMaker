package com.ebc.cupcakemaker.view.components.cupcakewizard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ebc.cupcakemaker.R
import com.ebc.cupcakemaker.enumerators.ViewIDs
import com.ebc.cupcakemaker.enumerators.ViewModelIDs
import com.ebc.cupcakemaker.view.components.CustomSpacer
import com.ebc.cupcakemaker.viewmodel.CupcakeMakerViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

fun createPickupOptions(): List<String> {
    val dateOptions = mutableListOf<String>()
    val dateFormat = SimpleDateFormat("E MMM d")
    val calendar = Calendar.getInstance()

    repeat(5) {
        dateOptions.add(dateFormat.format(calendar.time))
        calendar.add(Calendar.DATE,1)
    }



    return dateOptions
}

@Composable
fun SelectDateScreen(navController: NavController,
                     cupcakeMakerViewModel: CupcakeMakerViewModel
) {
    //Text (text = "Placeholderss")
    val dateOptions = createPickupOptions()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        //En esta columna vamos a albergar las opciones de pickup
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            dateOptions.forEach {
                Row(
                    modifier = Modifier.selectable(
                        selected = cupcakeMakerViewModel.state.pickupDate == it,
                        onClick = {
                            cupcakeMakerViewModel.onValue(it, ViewModelIDs.PickUpDate.id)
                        }
                    )
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = cupcakeMakerViewModel.state.pickupDate == it,
                        onClick = {
                            cupcakeMakerViewModel.onValue(it, ViewModelIDs.PickUpDate.id)
                        }
                    )
                    Text(text = it)

                }
            }
            CustomSpacer(height = 16.dp)
            TextField(
                value = cupcakeMakerViewModel.state.pickupInstructions,
                onValueChange = { instructions ->
                    cupcakeMakerViewModel.onValue(instructions, ViewModelIDs.PickUpInstructions.id)
                },
                label = { Text(stringResource(R.string.pickup_instructions)) },
                maxLines = Int.MAX_VALUE,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            )
            CustomSpacer(height = 16.dp)
            HorizontalDivider(
                thickness = 1.dp,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = stringResource(
                    R.string.subtotal,
                    cupcakeMakerViewModel.state.total
                ),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

        }

    }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1F),
            onClick = {
                cupcakeMakerViewModel.reset()
                navController.popBackStack(ViewIDs.Flavors.id, false)
            }
        ) {
            Text(stringResource(R.string.cancel))
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1F),
                onClick = {
                    cupcakeMakerViewModel.reset()
                    navController.popBackStack(ViewIDs.Flavors.id, false)
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1F),
                onClick = {
                    navController.navigate(ViewIDs.OrderSummary.id)
                },
                enabled = cupcakeMakerViewModel.state.pickupDate.isNotEmpty()
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}


