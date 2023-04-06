package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jetpack.carpartsfinder.dto.PartListItemData

@Composable
fun PartItemView(
    part: PartListItemData,
    onClick: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable(
                onClick = {
                    onClick(part.id)
                }
            )
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
        ) {
            Column {
                AsyncImage(
                    contentScale = ContentScale.Crop,
                    model = part.previewImage,
                    modifier = Modifier.size(48.dp),
                    contentDescription = "preview of car part"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
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
@Preview()
fun PreviewPartItemView() {
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















