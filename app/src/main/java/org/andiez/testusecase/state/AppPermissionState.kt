package org.andiez.testusecase.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState

/**
 * Created by AndiezSatria on 08/09/2023.
 */

@OptIn(ExperimentalPermissionsApi::class)
class AppPermissionState(
    val permissionState: MultiplePermissionsState,
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberAppPermissionState(
    permissionState: MultiplePermissionsState = rememberMultiplePermissionsState(permissions = Permissions.listOfPermission)
): AppPermissionState = remember {
    AppPermissionState(permissionState)
}