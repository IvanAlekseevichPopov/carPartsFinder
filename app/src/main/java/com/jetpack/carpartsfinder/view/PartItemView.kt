package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jetpack.carpartsfinder.dto.PartListItemData
import com.jetpack.carpartsfinder.ui.theme.AppMaterialTheme
import com.jetpack.carpartsfinder.ui.theme.dimens

@Composable
fun PartItemView(
    part: PartListItemData,
    onClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(
                onClick = {
//                    onClick(part.id)
                    onClick(part.partNumber) //TODO попробовать вынести наверх
                }
            )
            .padding(MaterialTheme.dimens.four)
            .fillMaxWidth()
            .shadow(MaterialTheme.dimens.four)
    ) {
        Row(
            modifier = Modifier
                .padding(MaterialTheme.dimens.eight)
                .fillMaxWidth(),
        ) {
            Column {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    model = part.previewImage,
                    modifier = Modifier.size(MaterialTheme.dimens.sixtyFour),
                    contentDescription = "preview of car part"
                )
            }
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.eight))
            Column {
                Text(
                    text = part.partNumber,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )

                Text(
                    text = "Manufacturer: ${part.manufacturer}",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPartItemView() {
    AppMaterialTheme() {
        Box(modifier = Modifier.size(300.dp)) {
            PartItemView(
                part = PartListItemData(
                    "uuid",
                    "as34wt",
                    "honda",
                    "asdfadsf"
                ),
                onClick = {}
            )
        }
    }
}















