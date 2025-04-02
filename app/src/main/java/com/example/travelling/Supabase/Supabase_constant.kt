package com.example.travelling.Supabase



import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.status.SessionSource
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object Constant {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://dzqlcdyoxtocojmossdr.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImR6cWxjZHlveHRvY29qbW9zc2RyIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDI5MDA3OTUsImV4cCI6MjA1ODQ3Njc5NX0.cjZTlbSsYlyo9T5PXZi5uKzWmlYRoQWkWGXjWG092U0"
    ) {
        install(Postgrest)
        install(Auth)
        install(Storage)
    }
}