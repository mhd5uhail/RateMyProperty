package com.mhdsuhail.ratemyproperty.di

import android.app.Application
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.json.*
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.data.room.RMPDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Define all singleton instances like databases access instances
    @Provides
    @Singleton
    fun provideRMPDatabase(app: Application): RMPDatabase {
        return RMPDatabase.getInstance(app.applicationContext)
    }

    @Provides
    @Singleton
    fun providePropertyRepository(db: RMPDatabase): PropertyRepository {
        return FakePropertyRepository()
    }

    @Provides
    @Singleton
    fun provideFeatureRepository(db: RMPDatabase): FeatureRepository {
        return FeatureRepositoryImpl(db.featureDao)
    }

    @Provides
    @Singleton
    fun providesSearchQueryRepository(db: RMPDatabase): SearchHistoryRepository {
        return SearchHistoryRepoImpl(db.searchQueryDao)
    }

    @Provides
    @Singleton
    fun providesPropertyDescriptionsRepository(db: RMPDatabase): PropertyDescriptionRepository {
        return PropertyDescriptionRepoImpl(db.descriptionsDao)
    }

    @Provides
    @Singleton
    fun providesCanadianProvinceParser() : JsonParser<CanadianProvince>{
        return CanadianProvinceParser()
    }

    @Provides
    @Singleton
    fun providesFeatureDataParser() : JsonParser<FeatureData>{
        return FeatureDataParser()
    }
    @Provides

    @Singleton
    fun providesUnitTypeDataParser() : JsonParser<UnitType>{
        return UnitTypeDataParser()
    }

}