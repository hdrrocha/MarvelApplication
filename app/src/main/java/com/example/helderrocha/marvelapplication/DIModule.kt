package com.example.helderrocha.marvelapplication

import android.content.Context
import com.example.helderrocha.marvelapplication.api.NetworkModule
import com.example.helderrocha.marvelapplication.view.listheroes.hero.HeroActivity
import com.example.helderrocha.marvelapplication.view.listheroes.listhero.ListHeroesActivity
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module(includes = [
    NetworkModule::class,
    SchedulerModule::class
])
class AppModule

@Module
abstract class AndroidInjectorsModule {
    @ContributesAndroidInjector
    abstract fun listHeroesActivity(): ListHeroesActivity
    @ContributesAndroidInjector
    abstract fun heroActivity(): HeroActivity

}

@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        AndroidInjectorsModule::class

))
interface AppComponent : AndroidInjector<MyApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>() {
        @BindsInstance
        abstract fun appContext(appContext: Context): Builder
    }
}