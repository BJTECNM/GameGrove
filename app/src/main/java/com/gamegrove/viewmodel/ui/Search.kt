package com.gamegrove.viewmodel.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.gamegrove.viewmodel.data.MyViewModel
import com.gamegrove.viewmodel.data.datagames.Game
import com.gamegrove.viewmodel.data.datagames.Games
import com.gamegrove.viewmodel.ui.elements.BottomBarNavigation
import com.gamegrove.viewmodel.ui.elements.GridItem
import com.gamegrove.viewmodel.ui.elements.TopAppBar

@Composable
fun Search(navController: NavHostController, myViewModel: MyViewModel) {
    Scaffold(
        topBar = { TopAppBar(navController, myViewModel) },
        bottomBar = { BottomBarNavigation(navController) }
    ) { innerPaddingValues ->
        Searching(navController, myViewModel, innerPaddingValues)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Searching(navController: NavHostController, myViewModel: MyViewModel, innerPaddingValues: PaddingValues) {
    var active by remember { mutableStateOf(false) }
    val search: String by myViewModel.search.observeAsState(initial = "")
    val searchResult: List<Game> by myViewModel.searchResult.observeAsState(listOf())

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues = innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            query = search,
            onQueryChange = { myViewModel.changeSearch(it) },
            onSearch = { active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text(text = "Nombre del juego") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    Modifier.clickable {
                        active = false
                    }
                )
            },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(68.dp)
                .padding(start = 16.dp, end = 16.dp)
        ) {
            if (search.isNotEmpty()) {
                val aux = Games.items.filter { it.title.contains(search, true) }
                myViewModel.changeSearchResult(aux)
            } else {
                myViewModel.changeSearchResult(listOf())
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (searchResult.isEmpty()) {
            Text(
                text = "Ingrese un nombre para mostrar las coincidencias",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(32.dp),
                fontSize = 22.sp
            )
        } else {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
                columns = GridCells.Fixed(count = 2)
            ) {
                items(searchResult) {
                    GridItem(navController, myViewModel, it)
                }
            }
        }

    }
}