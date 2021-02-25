package com.example.androiddevchallenge

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.androiddevchallenge.MainDestinations.PUPPY_DETAIL
import com.example.androiddevchallenge.MainDestinations.PUPPY_DETAIL_KEY
import com.example.androiddevchallenge.presentation.ui.PuppiesListView
import com.example.androiddevchallenge.presentation.ui.PuppyDetailView

object MainDestinations {
    const val PUPPY_LIST = "list"
    const val PUPPY_DETAIL = "detail"
    const val PUPPY_DETAIL_KEY = "detailId"
}


@Composable
fun NavGraph(startDestination: String = MainDestinations.PUPPY_LIST) {
    val navController = rememberNavController()
    val (canPop, setCanPop) = remember { mutableStateOf(false) }
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val actions = remember(navController) { MainActions(navController) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                navigationIcon = if (canPop) {
                    {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(Icons.Outlined.ArrowBack, "back")
                        }
                    }
                } else { null },
            )
        },
        scaffoldState = scaffoldState
    ){
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(MainDestinations.PUPPY_LIST) {
            setCanPop(false)
            PuppiesListView(callback = actions.selectPuppy)
        }

        composable(
            "$PUPPY_DETAIL/{$PUPPY_DETAIL_KEY}",
            arguments = listOf(navArgument(PUPPY_DETAIL_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            setCanPop(true)
            PuppyDetailView(
                puppyId  = arguments.getLong(PUPPY_DETAIL_KEY),
            )
        }
    }
}
}

/**
 * Models the navigation actions in the app.
 */
class MainActions(navController: NavHostController) {

    val selectPuppy: (Long) -> Unit = {
        navController.navigate("$PUPPY_DETAIL/$it")
    }

}