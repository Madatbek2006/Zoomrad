package uz.madatbek.zoomradcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.madatbek.zoomradcompose.utils.navigation.AppNavigator
import uz.madatbek.zoomradcompose.utils.navigation.NavigationDispatcher
import uz.madatbek.zoomradcompose.utils.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun bindAppNavigator(impl: NavigationDispatcher): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatcher): NavigationHandler


}


