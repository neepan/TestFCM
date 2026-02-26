package com.neepan.testfcm.data

import android.content.Context
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import com.neepan.testfcm.BuildConfig
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.functions.Functions
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime

lateinit var supabase: SupabaseClient

fun initSupabase(context: Context){

    supabase = createSupabaseClient(
        BuildConfig.SUPABASE_URL,
        BuildConfig.SUPABASE_KEY

    ){
        install(Auth)
        install(Postgrest)
        install(Realtime)
        install(Functions)
    }
}