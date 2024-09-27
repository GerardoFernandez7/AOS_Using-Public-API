package edu.uvg.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun CategoryScreen(modifier: Modifier = Modifier, navController: NavController){
    val categoryViewModel: MainViewModel = viewModel()
    val viewstate by categoryViewModel.categoriesState

    Box(modifier = Modifier.fillMaxSize()){
        when{
            viewstate.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewstate.error != null ->{
                Text("ERROR OCURRED")
            }

            else -> {
                CategoryScreen(categories = viewstate.list, navController)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navController: NavController){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier
        .fillMaxSize()) {
        items(categories){
                category -> CategoryItem(category = category, navController)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navController: NavController){
    Column (
        modifier = Modifier.padding(8.dp).fillMaxSize()
        , horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb)
            , contentDescription = null
            , modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable {
                    navController.navigate("meal/${category.strCategory}")
                }
        )

        Text(
            text = category.strCategory
            , color = Color.Black
            , style = TextStyle(fontWeight = FontWeight.Bold)
            , modifier = Modifier.padding(top=4.dp)
        )
    }
}