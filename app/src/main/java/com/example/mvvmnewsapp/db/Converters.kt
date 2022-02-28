package com.example.mvvmnewsapp.db

import androidx.room.TypeConverter
import com.example.mvvmnewsapp.models.Source

class Converters {

    // Source 클래스를 Primitive 타입인 String 으로 변환
    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    // String 으로 변환된 데이터를 다시 Source 형태로 변환
    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}