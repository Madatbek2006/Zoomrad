package uz.madatbek.zoomradcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import uz.madatbek.zoomradcompose.domain.CardRepository
import uz.madatbek.zoomradcompose.domain.DataRepository
import uz.madatbek.zoomradcompose.domain.HomeRepository
import uz.madatbek.zoomradcompose.domain.LoginRepository
import uz.madatbek.zoomradcompose.domain.TransferRepository
import uz.madatbek.zoomradcompose.domain.UserRepository
import uz.madatbek.zoomradcompose.domain.impl.CardRepositoryImpl
import uz.madatbek.zoomradcompose.domain.impl.DataRepositoryImpl
import uz.madatbek.zoomradcompose.domain.impl.HomeRepositoryImpl
import uz.madatbek.zoomradcompose.domain.impl.LoginRepositoryImpl
import uz.madatbek.zoomradcompose.domain.impl.TransferRepositoryImpl
import uz.madatbek.zoomradcompose.domain.impl.UserRepositoryImpl

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindLoginRepository(impl:LoginRepositoryImpl):LoginRepository

    @Binds
    fun bindDataRepository(impl:DataRepositoryImpl):DataRepository
    @Binds
    fun bindCardRepository(impl:CardRepositoryImpl): CardRepository

    @Binds
    fun bindUserRepository(impl:UserRepositoryImpl): UserRepository

    @Binds
    fun bindTransferRepository(impl:TransferRepositoryImpl): TransferRepository

    @Binds
    fun bindHomeRepository(impl:HomeRepositoryImpl):HomeRepository
}